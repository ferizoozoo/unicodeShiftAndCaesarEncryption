package encryptdecrypt;

interface Algorithm {
    String encrypt(String message, int key);
    String decrypt(String cypheredMessage, int key);
}

class ChoosedAlgorithm {
    Algorithm algorithm;

    ChoosedAlgorithm(String algorithm) {
        switch (algorithm) {
            case "shift":
                this.algorithm = new ShiftAlgorithm();
                break;
            case " ":
                this.algorithm = new ShiftAlgorithm();
                break;
            case "unicode":
                this.algorithm = new UnicodeAlgorithm();
                break;
            default:
                break;
        }
    }

    String encrypt(String message, int key) {
        return this.algorithm.encrypt(message, key);
    }

    String decrypt(String cypheredMessage, int key) {
        return this.algorithm.decrypt(cypheredMessage, key);
    }
}
