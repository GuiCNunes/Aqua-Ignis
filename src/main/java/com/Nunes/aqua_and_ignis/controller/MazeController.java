package com.Nunes.aqua_and_ignis.controller;

import com.Nunes.aqua_and_ignis.controller.entity.MonsterController;
import com.Nunes.aqua_and_ignis.controller.entity.PlayerController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.MazeGenerator;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.*;
import com.Nunes.aqua_and_ignis.states.LoseState;
import com.Nunes.aqua_and_ignis.states.MenuState;
import com.Nunes.aqua_and_ignis.states.WinState;
import com.Nunes.aqua_and_ignis.model.entity.monsterfactory.MonsterFactory;
import com.Nunes.aqua_and_ignis.model.entity.monsterfactory.RandomMonsterFactory;

import com.Nunes.aqua_and_ignis.model.menu.Menu;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

public class MazeController extends GameController {
    private PlayerController playerController;
    private MonsterController monsterController;
    private MazeGenerator generator;

    private final Random random = new Random();
    private final MonsterFactory monsterFactory;

    private long lastSpawn = 0;

    public MazeController(Maze maze) {
        super(maze);
        this.playerController = new PlayerController(maze);
        this.monsterController = new MonsterController(maze);
        this.generator = new MazeGenerator();
        this.generator.generate(maze);
        this.monsterFactory = new RandomMonsterFactory(random);
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {
        if (op == GUI.operation.quit) {
            game.setState(new MenuState(new Menu()));
            return;
        }

        spawnMonsters(time);
        monsterController.command(game, op, time);
        playerController.command(game, op, time);

        handleCollisions();

        if (checkLoss()) game.setState(new LoseState(new Menu()));
        else if (checkWin()) game.setState(new WinState(new Menu(), getModel().getCollectedStars(), getModel().getTime()));
    }

    private void handleCollisions() {
        checkItems(getModel().getIgnis());
        checkItems(getModel().getAqua());
    }

    private void checkItems(Player player) {
        Position p = player.getPosition();
        Iterator<Star> it = getModel().getStars().iterator();
        while (it.hasNext()) {
            if (it.next().getPosition().equals(p)) {
                getModel().incrementStars();
                it.remove();
            }
        }
        if (getModel().getKey() != null && getModel().getKey().getPosition().equals(p)) {
            getModel().setExitOpen(true);
            getModel().setKey(null);
        }
    }

    private boolean checkWin() {
        if (!getModel().isExitOpen()) return false;
        Position exit = getModel().getExit();

        boolean winIgnis = getModel().getIgnis().getPosition().equals(exit);
        boolean winAqua = getModel().getAqua().getPosition().equals(exit);

        return winIgnis && winAqua;
    }

    private boolean checkLoss() {
        for (Monster m : getModel().getMonsters()) {
            if (m.getPosition().equals(getModel().getIgnis().getPosition()) ||
                    m.getPosition().equals(getModel().getAqua().getPosition())) return true;
        }
        return false;
    }

    private void spawnMonsters(long time) {
        if (time - lastSpawn >= 10000 && getModel().getMonsters().size() < 15) {
            Position p = generator.getFreePosition(getModel());

            getModel().addMonster(monsterFactory.create(p));

            lastSpawn = time;
        }
    }
}