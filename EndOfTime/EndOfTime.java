/* 
 * A1 07-Sept-11
 * Ansha Yu | ay226 | 2392034
 */

import java.util.Date;

public class EndOfTime {

	public static void main(String[] args) {
		System.out.println(endOfTime().toString());
	}
	
	public static Date endOfTime() {
		return new Date(Long.MAX_VALUE);
	}
}