public class Computer {
    class Proccessor {
        private boolean isStart = false;

        public void start(){
            isStart = true;
        }

        public void shurDown(){
            isStart = false;
        }
    }
    class RAM {
        private boolean isStart = false;

        public void start(){
            isStart = true;
        }

        public void shurDown(){
            isStart = false;
        }
    }

    Proccessor i7 = new Proccessor();
    RAM transfer = new RAM();
}
