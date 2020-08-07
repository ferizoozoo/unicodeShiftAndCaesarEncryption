package encryptdecrypt;

class UnicodeAlgorithm implements Algorithm {
    @Override
    public String encrypt(String message, int key) {
        String cypheredMessage = "";
        for (int i = 0; i < message.length(); i++) {
            int characterCode = message.charAt(i);
            cypheredMessage += (char) (characterCode + key);
        }
        return cypheredMessage;
    }

    @Override
    public String decrypt(String cypheredMessage, int key) {
        String message = "";
        for (int i = 0; i < cypheredMessage.length(); i++)
        {
            int characterCode = cypheredMessage.charAt(i);
            message += (char)(characterCode - key);
        }
        return message;
    }
}
