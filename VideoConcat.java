import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * 视频拼接
 * @author zhangle
 *
 */
public class VideoConcat {
	/**
	 *  运行命令
	 * @param command
	 */
	public boolean runCmd(String command){
		try
			{          
		  		Runtime rt = Runtime.getRuntime();
		  		Process proc = rt.exec(command);
		        InputStream stderr = proc.getErrorStream();
		        InputStreamReader isr = new InputStreamReader(stderr);
		        BufferedReader br = new BufferedReader(isr);
		        String line = null;
		        System.out.println("<ERROR>");
		        while ( (line = br.readLine()) != null) {
		        	System.out.println(line);
		        }
		        System.out.println("</ERROR>");
		        int exitVal = proc.waitFor();
		        System.out.println("Process exitValue: " + exitVal);
		        return true;
		  	} catch (Throwable t) {
		    	t.printStackTrace();
		    	return false;
		    }
	}
	/**
	 *  合并视频
	 * @param infiles
	 * @param outfile
	 * @return
	 */
	public boolean concat(String listfile, String outfile) {
		//TODO 
		boolean flag = false;
//		String filesStr = "";
//		for(int i=1; i<=infiles.size(); i++) {
//			filesStr = filesStr.concat(infiles.get(i-1));
//			if(i != infiles.size()) {
//				String appendStr = "|";
//				filesStr = filesStr.concat(appendStr);
//			}
//		}
//		System.out.println(filesStr);
//		String avitompg;
//		//String intermediate;
//		Runtime rt = Runtime.getRuntime();
//		
//		//convert avi to mpg
//		for(int i=1; i<=infiles.size(); i++) {
//			avitompg = "ffmpeg -i " + infiles.get(i-1) + " -qscale:v 1 /home/zhangle/intermediate" + i + ".mpg";
//			System.out.println("avitompg = " + avitompg);
//			//flag = runCmd(avitompg);
//		}
//		//
//		String concatStr = "";
//		for(int i=1; i<=infiles.size(); i++) {
//			concatStr = concatStr + "/home/zhangle/intermediate" + i + ".mpg";
//			if(i != infiles.size()) {
//				String appendStr = "|";
//				concatStr = concatStr.concat(appendStr);
//			}
//		}
		//String listfile = "/home/zhangle/list.txt";
		String concatStr = "ffmpeg -f concat -i "+listfile+ " -codec copy "+ outfile;
		System.out.println(concatStr);
		flag = runCmd(concatStr);
		return flag;
	}
	/**
	 *  主函数
	 * @param args
	 */
	public static void main(String args[]) {
//		int N = 3;
//		Vector<String> infiles = new Vector<String>(N);
//		for(int i=1; i<=N; i++) {
//			String file = "/home/zhangle/input" + i + ".avi";
//			infiles.add(file);
//		}
		String listfile = "/home/zhangle/list.txt";
		String outfile = "/home/zhangle/all.avi";
		VideoConcat vc = new VideoConcat();
		vc.concat(listfile, outfile);
	}
}
