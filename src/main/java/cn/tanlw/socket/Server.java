package cn.tanlw.socket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*实现登陆与注册 功能。
客户端与服务端连接的时候，就要提示客户端请选择功能。

客户端注册的时候，用户名与密码都是发送给服务端 的，服务端需要把数据保存到服务端的文件上。

登陆： 登陆的时候客户端输入用户名与密码发送给服务端，服务端需要校验，返回结果给客户端。*/
class Register extends Thread{
    Socket socket;
    public Register(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            /*获取服务端的socket输出流对象用于还送信息 */
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //创建输入流获取客户端输入
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                bufferedWriter.write("请选择功能 ：A（注册），B（登录）"+"\r\n");
                bufferedWriter.flush();
                String option=bufferedReader.readLine();
                if("A".equalsIgnoreCase(option)){
                    register(bufferedReader, bufferedWriter);
                    System.out.println("执行到了a");
                    continue;

                }else if("B".equalsIgnoreCase(option)){
                    System.out.println("执行到了b");
                    login(bufferedReader, bufferedWriter);
                    continue;

                }else{
                    bufferedWriter.write("输入有误，请重新输入"+"\r\n");
                    bufferedWriter.flush();
                    System.out.println("执行到了c");
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册
     * @param bufferedReader
     * @param messageWriter
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void register(BufferedReader bufferedReader, BufferedWriter messageWriter) throws IOException, FileNotFoundException {
        messageWriter.write("请输入用户名："+"\r\n");
        messageWriter.flush();
        String username=bufferedReader.readLine();
        messageWriter.write("请输入密码："+"\r\n");
        messageWriter.flush();
        String password=bufferedReader.readLine();
        //创建存取文件
        File file=new File("D:\\IOTest\\user.txt");
        if(!file.exists()){
            //手动添加目录
            file.createNewFile();

            //第一次创建时 直接添加
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(username+password);
            bufferedWriter.newLine();
            bufferedWriter.close();
            //还送信息
            messageWriter.write("注册成功"+"\r\n");
        }else{
            //创建文件的输入流判断文件内是否存此用户
            boolean flag=false;
            /*注册失败重试循环*/
            while(!flag){
                BufferedReader fileReader=new BufferedReader(new FileReader(file));
                String  length=null;
                while((length=fileReader.readLine())!=null){
                    if(length.equals(username+password)){
                        flag=true;
                        messageWriter.write("注册失败 存在同名用户或密码"+"\r\n");
                        break;
                    }
                }
                if(!flag){
                    BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true));
                    bufferedWriter.write(username+password);
                    bufferedWriter.newLine();
                    bufferedWriter.close();

                    //还送信息
                    messageWriter.write("注册成功"+"\r\n");
                    fileReader.close();
                    break;
                }

                fileReader.close();
                //还送信息
                messageWriter.write("请重新输入用户名："+"\r\n");
                messageWriter.flush();
                username=bufferedReader.readLine();
                messageWriter.write("请输入密码："+"\r\n");
                messageWriter.flush();
                password=bufferedReader.readLine();
            }
        }
    }

    public void login(BufferedReader bufferedReader,BufferedWriter messageWriter) throws IOException, FileNotFoundException {
        //创建Socket的输入流对象
        messageWriter.write("请输入用户名："+"\r\n");
        messageWriter.flush();
        String username=bufferedReader.readLine();
        messageWriter.write("请输入密码："+"\r\n");
        messageWriter.flush();
        String password=bufferedReader.readLine();
        //判断文件内是否存在相同用户
        File file=new File("D:\\IOTest\\user.txt");
        BufferedReader fileReader2=new BufferedReader(new FileReader(file));
        String content=null;
        boolean flag=false;
        while((content=fileReader2.readLine())!=null){

            if(!content.equals(username+password)){
            }else{
                flag = true;
                messageWriter.write("登录成功"+"\r\n");
                break;
            }
        }
        if(!flag){
            messageWriter.write("登录失败 存在错误账号或密码"+"\r\n");
        }
        fileReader2.close();
    }
}

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(6666);
        System.out.println("服务器已启动...");
        Socket socket=serverSocket.accept();
        new Register(socket).start();
    }
}

