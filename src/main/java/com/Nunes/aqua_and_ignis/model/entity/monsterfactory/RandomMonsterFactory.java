package com.Nunes.aqua_and_ignis.model.entity.monsterfactory;

import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Ghost;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.model.entity.Slime;

import java.util.Random;

public class RandomMonsterFactory implements MonsterFactory {

    private final Random random;

    public RandomMonsterFactory(Random random) {
        this.random = random;
    }

    @Override
    public Monster create(Position p) {
        if (random.nextBoolean()) return new Slime(p.getX(), p.getY());
        return new Ghost(p.getX(), p.getY());
    }
}