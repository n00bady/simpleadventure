public class mainAdventure {
    public static void main (String args[]) throws Exception {
        // print banner title
        System.out.println("\u001B[38;5;199m");
        System.out.println("""
                  ___| _)                 |         \\        |                  |                 \s
                \\___ \\  | __ `__ \\  __ \\  |  _ \\   _ \\    _` |\\ \\   / _ \\ __ \\  __| |   |  __| _ \\\s
                      | | |   |   | |   | |  __/  ___ \\  (   | \\ \\ /  __/ |   | |   |   | |    __/\s
                _____/ _|_|  _|  _| .__/ _|\\___|_/    _\\\\__,_|  \\_/ \\___|_|  _|\\__|\\__,_|_|  \\___|\s
                                   _|                                                             \s""");
        System.out.println("\u001b[0m");

        mainLoop startGame = new mainLoop();
        startGame.startLoop();
    }
}
