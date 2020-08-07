package encryptdecrypt;

class ShiftAlgorithm implements Algorithm {
    @Override
    public String encrypt(String message, int key) {
        String cypheredMessage = "";
        for (int i = 0; i < message.length(); i++) {
            int characterCode = message.charAt(i);
            if (65 <= characterCode && characterCode <= 90) {
                cypheredMessage += characterCode + key < 90
                        ? (char) (characterCode + key)
                        : (char) (64 + (characterCode + key) % 90);
            } else if (97 <= characterCode && characterCode <= 122) {
                cypheredMessage += characterCode + key < 122
                        ? (char) (characterCode + key)
                        : (char) (96 + (characterCode + key) % 122);
            } else {
                cypheredMessage += (char) characterCode;
            }
        }
        return cypheredMessage;
    }

    @Override
    public String decrypt(String cypheredMessage, int key) {
        String message = "";
        for (int i = 0; i < cypheredMessage.length(); i++)
        {
            int characterCode = cypheredMessage.charAt(i);
            if (65 <= characterCode && characterCode <= 90) {
                message += characterCode - key >= 65
                        ? (char) (characterCode - key)
                        : (char) (26 + characterCode - key);
            } else if (97 <= characterCode && characterCode <= 122) {
                message += characterCode - key >= 97
                        ? (char) (characterCode - key)
                        : (char) (26 + characterCode - key);
            } else {
                message += (char) characterCode;
            }
        }
        return message;
    }
}
