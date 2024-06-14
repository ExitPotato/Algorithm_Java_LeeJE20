# 실수
## 그래프
- 인접 행렬 사용 시 각 정점별 List 초기화 잊지 말기
- BFS는 PQ가 아니라 Q를 쓴다

## 수학
- 수학적으로 잘 모르겠으면 직감으로 하지 말고 코드로 하자.

---
# 보완점
## 조합
- 순조부에서 개수 구할때는 기저조건에서 리턴에서 1 리턴하고, for문 부분에서 그 값들을 더해서 for문 이후에 리턴한다.

## 정렬
### 리스트 정렬
``` java
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder())
list.sort(Comparator.naturalOrder());
list.sort(Comparator.reverseOrder());
```
