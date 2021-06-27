package Facade.src;

public class Writer {

    private String fileName;

    public Writer(String fileName) {
        this.fileName = fileName;
    }

    public void fileConnect() {
        String msg = String.format("Writer %s로 연결합니다.", fileName);
        System.out.println(msg);
    }

    public void fileDisconnect() {
        String msg = String.format("Writer %s의 내용을 읽어 옵니다.", fileName);
        System.out.println(msg);
    }

    public void write() {
        String msg = String.format("Writer %s로 파일 쓰기합니다.", fileName);
        System.out.println(msg);
    }
}
