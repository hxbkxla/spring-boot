import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Path path = Paths.get("D:/temp/dir/60de2030-fe08-4739-bba9-37c037e44635_Agentno逻辑重构的测试数据.txt");
		AsynchronousFileChannel afc=AsynchronousFileChannel.open(path);  
		ByteBuffer buffer = ByteBuffer.allocate(1000);  
        afc.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result , ByteBuffer attachment) {
				 System.out.println("Read " + result + " content is : " + new String(attachment.array(),Charset.forName("UTF-8")));
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				System.err.println(exc.getMessage()); 
			}}); 
        while (true)  
        {  
        	Thread.sleep(1000);
            System.out.println("do others.");  
        }  
	}

}
