package cn.tanlw.socket;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 6666);
            //创建socket的输入流对象
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //创建socket的输出流对象
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //创建获取键盘输入的对象
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                /**
                 * 输出服务端返回，包括换行
                 */
                char[] buffer = new char[1024];
                if ((bufferedReader.read(buffer)) != 0) {
                    System.out.print(new String(buffer));
                }
                String respect = keyboardReader.readLine();
                //根据指令开始发送数据
                bufferedWriter.write(respect + "\r\n");
                bufferedWriter.flush();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

