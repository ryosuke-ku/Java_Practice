//----------------------
// ネットからデータを読み取り，ファイルに書き込む
//---------------------
import java.net.*;
import java.io.*;
public class NetRead {
	public static void main(String args[]){
	
		try{ //概ねの操作で例外処理が必要です。
			 //URLを作成する
             
                     FileWriter wr=new FileWriter("4.txt");//Fileとアプリを繋ぐ書き込みでつなぐ  

                    BufferedWriter br=new BufferedWriter(wr);//BufferedWriterを作成


			 String adress="http://www.geocities.jp/inu_poti/java/sample/Hellow.java";
			 URL url=new URL(adress);//URLを設定
			 
		     // URL接続
			  HttpURLConnection connect = (HttpURLConnection)url.openConnection();//サイトに接続
		      connect.setRequestMethod("GET");//プロトコルの設定
		      InputStream in=connect.getInputStream();//ファイルを開く
		      
		      // ネットからデータの読み込み
		      String str;//ネットから読んだデータを保管する変数を宣言
		      str=readString(in);//1行読み取り
                    
                     while (str!=null) {//読み取りが成功していれば
		        System.out.println(str);//コンソールに出力

                        br.write(str);//文字列の書き込み①
			br.newLine();//改行を入れる

		        str=readString(in);//次を読み込む
		      }
		  
                        br.flush();//flush
			wr.flush();
			br.close();//閉じる
			wr.close();

		      // URL切断
		      in.close();//InputStreamを閉じる
		      connect.disconnect();//サイトの接続を切断

		}catch(Exception e){
			//例外処理が発生したら、表示する
			System.out.println("Err ="+e);
		}
	}
	
	//InputStreamより１行だけ読む（読めなければnullを返す）
	static String readString(InputStream in){
		try{
			int l;//呼んだ長さを記録
			int a;//読んだ一文字の記録に使う
			byte b[]=new byte[2048];//呼んだデータを格納
			a=in.read();//１文字読む
			if (a<0) return null;//ファイルを読みっていたら、nullを返す
			l=0;
			while(a>10){//行の終わりまで読む
				if (a>=' '){//何かの文字であれば、バイトに追加
					b[l]=(byte)a;
					l++;
				}
				a=in.read();//次を読む
			}
			return new String(b,0,l);//文字列に変換
		}catch(IOException e){
			//Errが出たら、表示してnull値を返す
			System.out.println("Err="+e);
			return null;
		}
	}
}
