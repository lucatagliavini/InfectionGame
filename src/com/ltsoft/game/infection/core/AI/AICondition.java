package com.ltsoft.game.infection.core.AI;

import com.ltsoft.game.infection.core.AI.states.AIState;
import com.ltsoft.game.infection.core.game.entity.NpcObject;
import com.ltsoft.game.infection.core.states.GameState;

public interface AICondition {

    boolean isMet(AIState state, NpcObject currentNpc);


}
