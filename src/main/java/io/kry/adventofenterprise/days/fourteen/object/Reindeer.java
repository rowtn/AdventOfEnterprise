package io.kry.adventofenterprise.days.fourteen.object;

import io.kry.adventofenterprise.days.fourteen.builders.ReindeerBuilder;

public class Reindeer {
        private int moveTime, waitTime, c, speed, travelled = 0;
        private Action current = Action.MOVE;

        public Reindeer(int speed, int moveTime, int waitTime) {
            this.speed = speed;
            this.moveTime = moveTime;
            this.waitTime = waitTime;
        }

        public static ReindeerBuilder builder() {
            return new ReindeerBuilder();
        }

        public void tick() {
            c++;
            if (current == Action.MOVE) {
                travelled += speed;
                if (c == moveTime) {
                    current = Action.STAY;
                    c = 0;
                }
            } else if (c == waitTime) {
                current = Action.MOVE;
                c = 0;
            }
        }

        static enum Action {
            MOVE, STAY
        }

        public int getTravelled() {
            return travelled;
        }
}
