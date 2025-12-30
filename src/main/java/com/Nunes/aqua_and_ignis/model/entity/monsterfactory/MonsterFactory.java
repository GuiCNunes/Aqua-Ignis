package com.Nunes.aqua_and_ignis.model.entity.monsterfactory;

import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Monster;

public interface MonsterFactory {
    Monster create(Position p);
}