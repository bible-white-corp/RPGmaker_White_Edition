package editor.ProjectTree;

public class pair {
        public pair(int index, String name) {
            this.index = index;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public int index;
        public String name;
}