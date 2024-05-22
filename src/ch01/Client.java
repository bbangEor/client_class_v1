package ch01;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// 클라이언트측 소켓 통신을 만들기 위한 준비물
		// 1. 서버측 컴퓨터의 주소 : 포트번호
		//

		// 생성자 매개변수에 서버측(IP 주소 , 포트번호)
		// "127.0.0.1" < 자기자신의 IP주소,< localhost 문자열로
		try (Socket socket = new Socket("localhost", 5000)) {
			// 객체 생성시 서버측과 연결이 되었다면
			// 스트림 활용이 가능하다.
			// ! 대상은 소켓이다 !
			OutputStream output = socket.getOutputStream(); // 소켓에서 기반스트림꺼낸것
			PrintWriter writer = new PrintWriter(output, true); // 기능확장을 위해 보조스트림 사용한것
			writer.println("재모웅니를 모르는 병호. .  ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
