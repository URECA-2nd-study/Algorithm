package yeoeun.week15;

public class Week15_문자열_압축 {
    public int solution(String s) {
        int len = s.length();
        StringBuilder sb;

        int minLen = (s.length() == 1) ? 1 :Integer.MAX_VALUE;
        for(int i = 1; i <= len/2; i++) {
            int start = 0; // 여기부터 쪼개기
            sb = new StringBuilder();

            String prefix = "";
            int count = 0;
            while(true) {
                if(s.substring(start).length() >= i) {
                    String cur = s.substring(start, start + i);

                    if(cur.equals(prefix)) { // prefix와 동일하면 count 증가
                        count++;
                    } else { // prefix와 다른 경우
                        if(start != 0 && count > 1) sb.append(count);
                        sb.append(prefix);

                        // 값 재설정
                        prefix = cur;
                        count = 1;
                    }
                    start += i;
                } else { // 더 이상 검사할 수 없으면 마무리 작업 후 문자열 생성
                    if(start != 0 && count > 1) sb.append(count);
                    sb.append(prefix);
                    sb.append(s.substring(start));
                    break;
                }
            }

            // 최소인 경우 갱신
            if(minLen > sb.length()) {
                minLen = sb.length();
            }
        }
        return minLen;
    }
}
