package Task;

public enum TaskStatus {
    IN_PROGRESS {
        @Override
        public String toString() {
            return "in progress";
        }
    },

    DONE {
        @Override
        public String toString() {
            return "done";
        }
    },

    CANCELLED {
        @Override
        public String toString() {
            return "cancelled";
        }
    }
}
