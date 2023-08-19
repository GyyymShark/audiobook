# audiobook
고민점  
1. 어떻게 책 내용을 저장할 것인가?(텍스트파일 전처리)
2. 어떻게 내용을 불러올 것인가?

```
1번은 인터넷으로 부터 다운받은 텍스트파일을
공백 제거 사이트 https://removelinebreaks.net/
에서 Auto, Space, None으로 Convert한 후, 다운받는다
이를 통해 모든 \n을 제거한다
이 상태에서 문자열을 DB에 저장한다
```

## 어떻게 문장을 분할할 것인가?

문장 분할 알고리즘 구현

```
2번은 문장 분할 알고리즘을 구현해  
내용을 반환해 주었다
.을 기준으로 문장을 반환하고 싶었지만, 쌍따옴표가 있는 문장에서 
고려할 케이스가 많아지게 되었다.  
자세한 내용은 밑에 순서도로 정리했다.
```

### 문장 분할 알고리즘(1)
![문장 분할 알고리즘(1)](https://github.com/GyyymShark/audiobook/assets/46774346/23d2baf5-2e44-44a3-bf12-0162477cdf19)

### 문장 분할 알고리즘(2)
![순서도-문장 분할 알고리즘(2) drawio](https://github.com/GyyymShark/audiobook/assets/46774346/c14ac6eb-2cba-4747-a3ce-f37a6508d077)


### api 순서도
![api 순서도](https://github.com/GyyymShark/audiobook/assets/46774346/0b5e0d05-3031-412e-a2ae-5f924b18d933)
