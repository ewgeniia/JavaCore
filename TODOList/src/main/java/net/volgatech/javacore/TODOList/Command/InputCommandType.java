package Command;

public enum InputCommandType {
    ADD_LIST {
        @Override
        public String toString() {
            return "AddList";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: AddList <ListName>";
        }
    },

    DELETE_LIST {
        @Override
        public String toString() {
            return "DeleteList";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: DeleteList <ListID>";
        }
    },

    EDIT_LIST_NAME {
        @Override
        public String toString() {
            return "EditListName";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: EditListName <ListID> <Name>";
        }
    },

    ADD_TASK {
        @Override
        public String toString() {
            return "AddTask";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: AddTask <Text> [<ListID>]";
        }
    },

    DELETE_TASK {
        @Override
        public String toString() {
            return "DeleteTask";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: DeleteTask <TaskID> <ListID>";
        }
    },

    EDIT_TASK_STATUS {
        @Override
        public String toString() {
            return "EditTaskStatus";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: EditTaskStatus <TaskID> <ListID> <Status>";
        }
    },

    EDIT_TASK_TEXT {
        @Override
        public String toString() {
            return "EditTaskText";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: EditTaskText <TaskID> <ListID> <Text>";
        }
    },

    SAVE {
        @Override
        public String toString() {
            return "Save";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: Save <FilePath>";
        }
    },

    IMPORT {
        @Override
        public String toString() {
            return "Import";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: Import [<FilePath>]";
        }
    },

    SHOW {
        @Override
        public String toString() {
            return "Show";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: Show {no parameters here}";
        }
    },

    HELP {
        @Override
        public String toString() {
            return "Help";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: Help {no parameters here}";
        }
    },

    EXIT {
        @Override
        public String toString() {
            return "Exit";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: Exit {no parameters here}";
        }
    };

    public abstract String getHelpMessage();
}
