# audiobook
고민점  
1. 어떻게 책 내용을 저장할 것인가?
2. 어떻게 내용을 불러올 것인가?


1번은 인터넷으로 부터 다운받은 텍스트파일을
공백 제거 사이트 https://removelinebreaks.net/
에서 Auto, Space, None으로 Convert한 후, 다운받는다
이를 통해 모든 \n을 제거한다

그 후에, 구분자를 추가하는 코드를 실행한다
## 동작원리
구분자는 '@'로 지정했으며 '.' 뒤에 '@'를 추가하여 문장을 구분한다
다만, " "쌍따옴표가 있는 경우에는  "어린왕자가 말을 했다.@"
문장의 끝이 '"'가 되어야함으로
'.@\"'를 '.\"@'로 변환하는 과정이 추가로 들어가게된다.

2번은 api 호출 시, 몇 문장을 불러올 것인지 개수를 파라미터로 전달받은 후,
\n의 개수와 일치할때까지 나온 문장들을 반환한다

3문장을 반환받던, 5문장을 반환 받던, db서버에 책 내용을 전부 저장해놓았기 떄문에,
요청 한번에 모든 내용을 db로 부터 불러오고, 파싱을 통해 문장 개수를 지정한대로 반환한다.
즉, 조회 요청은 모든 책 내용을 반환한다.

더 효율적으로 처리하려면 서버에 파일을 저장해 놓고,
db에는 파일의 이름과 경로만 저장한 후,
해당 요청에 따라 파일에서 문장 개수를 리턴해주면 더 효율적일 것이다.

하지만 배포를 고려했을때, 비효율적이라도 우선 구현을 위해 db에 책 내용을 저장하도록 하겠다.
