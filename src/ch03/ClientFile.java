package ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFile {

	public static void main(String[] args) {

		// 클라이언트 측 준비물
		// 1. 서버측 IP 주소와 포트번호
		// 2. 서버측 소켓과 연결될 소켓이 필요하다.

		Socket socket = null;

		try {
			socket = new Socket("localhost", 5001);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.print("안녕 반가워 ^^* \n");
			writer.flush();

			// 서버로 부터 데이터를 받기 위한 입력 스트림이 필요하다.

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String message = reader.readLine();
			System.out.println("서버측 응답 : " + message);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
