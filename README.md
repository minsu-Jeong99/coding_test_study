# coding_test_study

백준 · 프로그래머스 · 구름 코딩 테스트 풀이 기록 **(Java)**

문제마다 접근 방식을 `//1. //2. ...` 주석으로 코드 상단에 남기고,
시간초과가 났거나 돌아간 풀이도 지우지 않고 주석으로 남겨 비교할 수 있게 정리했습니다.

| 항목 | 내용 |
| --- | --- |
| 언어 | Java |
| 입출력 | `BufferedReader` + `StringTokenizer` (필요 시 `Scanner`) |
| 폴더 규칙 | `플랫폼/(알고리즘 유형)/플랫폼+문제번호/Main.java` |
| 진행 | 백준 20 · 프로그래머스 34 · 구름 6 — **총 60문제** |

이 문서는 **① 알고리즘 유형별**, **② 자료구조별** 두 축으로 정리되어 있습니다.

---

# ① 알고리즘 유형별 정리

## 🔵 BFS — 최단 거리 · 최소 횟수 · 동시 확산

> 큐에 시작점을 넣고 한 겹씩 퍼뜨린다. **"몇 칸 / 몇 번"** 을 묻는 문제의 기본기.
> `Queue<int[]> q = new LinkedList<>()` + `dx/dy` + `visited` 가 공통 뼈대.

| 문제 | 플랫폼 | 핵심 아이디어 | 사용 자료구조 |
| --- | --- | --- | --- |
| [1260 DFS와 BFS](https://www.acmicpc.net/problem/1260) | 백준 | 인접 리스트 기본 탐색, 번호 오름차순 방문 | `ArrayList[]`, `Queue` |
| [2178 미로 탐색](https://www.acmicpc.net/problem/2178) | 백준 | 격자 최단 경로, 칸에 거리 누적 | `int[][]`, `Queue` |
| [1697 숨바꼭질](https://www.acmicpc.net/problem/1697) | 백준 | 1차원 좌표에서 `x-1, x+1, x*2` 전이 | `boolean[]`, `Queue` |
| [7576 토마토](https://www.acmicpc.net/problem/7576) | 백준 | **다중 시작점** BFS로 동시 확산 | `int[][]`, `Queue` |
| [2606 바이러스](https://www.acmicpc.net/problem/2606) | 백준 | 1번 정점에서 도달 가능한 정점 수 | `ArrayList[]`, `Queue` |
| [11724 연결 요소의 개수](https://www.acmicpc.net/problem/11724) | 백준 | 미방문 정점마다 탐색 시작 → 횟수 세기 | `ArrayList[]`, `Queue` |
| [1012 유기농 배추](https://www.acmicpc.net/problem/1012) | 백준 | 격자 연결 요소 개수 | `int[][]`, `Queue` |
| [2667 단지번호붙이기](https://www.acmicpc.net/problem/2667) | 백준 | 영역별 크기를 모아 오름차순 출력 | `boolean[][]`, `ArrayList` |
| [14502 연구소](https://www.acmicpc.net/problem/14502) | 백준 | 벽 3개 조합(백트래킹) → 바이러스 확산 BFS | `int[][]`, `Queue` |
| [154538 숫자 변환하기](https://school.programmers.co.kr/learn/courses/30/lessons/154538) | 프로그래머스 | `+n, ×2, ×3` 상태 전이의 최소 연산 횟수 | `Queue`, `HashSet` |

## 🟢 DFS — 영역 · 연결 요소 · 트리 순회

> 재귀로 끝까지 파고든다. **"몇 덩어리 / 얼마나 넓은가 / 어디까지 이어지는가"** 를 묻는 문제.

| 문제 | 플랫폼 | 핵심 아이디어 | 사용 자료구조 |
| --- | --- | --- | --- |
| [4963 섬의 개수](https://www.acmicpc.net/problem/4963) | 백준 | **8방향** dx/dy 확장 | `int[][]`, `boolean[][]` |
| [1926 그림](https://www.acmicpc.net/problem/1926) | 백준 | 영역 개수 + 최대 넓이 동시 계산 | `int[][]`, `ArrayList` |
| [2583 영역 구하기](https://www.acmicpc.net/problem/2583) | 백준 | 직사각형을 격자에 칠한 뒤 영역 넓이 | `int[][]`, `ArrayList` |
| [2468 안전 영역](https://www.acmicpc.net/problem/2468) | 백준 | 높이 기준을 1씩 올리며 **반복 탐색** | `int[][]`, `ArrayList` |
| [10026 적록색약](https://www.acmicpc.net/problem/10026) | 백준 | 같은 격자를 정상/색약 두 번 탐색 | `char[][]`, `boolean[][]` |
| [1707 이분 그래프](https://www.acmicpc.net/problem/1707) | 백준 | 인접 정점을 반대 색으로 칠하며 모순 검사 | `ArrayList[]`, `int[]` |
| [2644 촌수계산](https://www.acmicpc.net/problem/2644) | 백준 | 트리 경로 깊이를 재귀 인자로 전달 | `ArrayList[]` |
| [11725 트리의 부모 찾기](https://www.acmicpc.net/problem/11725) | 백준 | 루트에서 내려가며 부모 기록 | `ArrayList[]`, `int[]` |
| [1520 내리막 길](https://www.acmicpc.net/problem/1520) | 백준 | DFS + **메모이제이션** (아래 DP 참고) | `int[][] dp` |
| 163021 퍼져나가는 소문 | 구름 | 연결 요소 크기 세기 | `ArrayList[]` |

## 🟣 백트래킹 — 고르고 → 되돌리기

> 선택 → 재귀 → **선택 취소**. 모든 경우를 보되 가지치기로 줄인다.

| 문제 | 플랫폼 | 핵심 아이디어 |
| --- | --- | --- |
| [1987 알파벳](https://www.acmicpc.net/problem/1987) | 백준 | 방문한 알파벳을 표시했다가 복귀 시 해제 |
| [14502 연구소](https://www.acmicpc.net/problem/14502) | 백준 | 빈 칸 중 벽 3개를 고르는 조합 + BFS 검증 |

## 🟠 DP — 점화식 · 메모이제이션

| 문제 | 플랫폼 | 핵심 아이디어 |
| --- | --- | --- |
| [12900 2 x n 타일링](https://school.programmers.co.kr/learn/courses/30/lessons/12900) | 프로그래머스 | `f(n) = f(n-1) + f(n-2)` (피보나치 꼴) |
| [12914 멀리 뛰기](https://school.programmers.co.kr/learn/courses/30/lessons/12914) | 프로그래머스 | 같은 피보나치 점화식, 모듈러 연산 |
| [43105 정수 삼각형](https://school.programmers.co.kr/learn/courses/30/lessons/43105) | 프로그래머스 | 위에서 그리디로 내려가면 실패 → **Bottom-up** 누적 |
| [2839 설탕 배달](https://www.acmicpc.net/problem/2839) | 백준 | 5kg 우선 그리디 / DP 두 관점 비교 |
| [1520 내리막 길](https://www.acmicpc.net/problem/1520) | 백준 | 단순 DFS는 시간초과 → `dp[x][y]`에 경로 수 캐싱 |

## 🔴 그리디 — 매 순간 최선

| 문제 | 플랫폼 | 핵심 아이디어 |
| --- | --- | --- |
| [12941 최솟값 만들기](https://school.programmers.co.kr/learn/courses/30/lessons/12941) | 프로그래머스 | 한쪽은 오름차순 × 다른 쪽은 내림차순 |
| [42883 큰 수 만들기](https://school.programmers.co.kr/learn/courses/30/lessons/42883) | 프로그래머스 | 스택 top이 더 작으면 pop (그리디 + 스택) |
| [148653 마법의 엘리베이터](https://school.programmers.co.kr/learn/courses/30/lessons/148653) | 프로그래머스 | 자릿수마다 올림/내림 중 비용이 싼 쪽 |
| [142085 디펜스 게임](https://school.programmers.co.kr/learn/courses/30/lessons/142085) | 프로그래머스 | 지금까지 중 **가장 큰 소모**에 무적권 사용 |
| [138476 귤 고르기](https://school.programmers.co.kr/learn/courses/30/lessons/138476) | 프로그래머스 | 개수 많은 종류부터 채우면 종류 수 최소 |
| [42747 H-Index](https://school.programmers.co.kr/learn/courses/30/lessons/42747) | 프로그래머스 | 정렬 후 `길이 - i`(남은 논문 수)와 비교 |

## 🟡 완전탐색 · 시뮬레이션

> 조건을 그대로 코드로 옮기는 구현력 문제. 상태를 어디에 담을지가 관건.

| 문제 | 플랫폼 | 핵심 아이디어 |
| --- | --- | --- |
| [42840 모의고사](https://school.programmers.co.kr/learn/courses/30/lessons/42840) | 프로그래머스 | 3명의 패턴을 2차원 배열로 묶어 일괄 비교 |
| [86491 최소직사각형](https://school.programmers.co.kr/learn/courses/30/lessons/86491) | 프로그래머스 | 회전 허용 → 큰 값은 w, 작은 값은 h로 갱신 |
| [389479 서버 증설 횟수](https://school.programmers.co.kr/learn/courses/30/lessons/389479) | 프로그래머스 | 시간대별 필요 서버 수와 만료 시각 관리 |
| [92341 주차 요금 계산](https://school.programmers.co.kr/learn/courses/30/lessons/92341) | 프로그래머스 | 차량별 누적 시간 집계 후 요금 계산 |
| [17680 [1차] 캐시](https://school.programmers.co.kr/learn/courses/30/lessons/17680) | 프로그래머스 | **LRU** — 히트 시 맨 뒤로, 가득 차면 앞에서 제거 |
| [12981 영어 끝말잇기](https://school.programmers.co.kr/learn/courses/30/lessons/12981) | 프로그래머스 | 탈락 시 `번호 = i%n+1`, `차례 = i/n+1` |
| 347925 문서 편집기 | 구름 | `a/d/u` 커맨드, undo를 위해 실행 이력 보관 |
| 351306 학생 정보 관리 | 구름 | `add` / `find` 커맨드 + 접두어 검색 |

## 🟤 문자열 처리

| 문제 | 플랫폼 | 핵심 아이디어 |
| --- | --- | --- |
| [12926 시저 암호](https://school.programmers.co.kr/learn/courses/30/lessons/12926) | 프로그래머스 | 아스키 연산 + 대소문자 분기 |
| [17682 [1차] 다트 게임](https://school.programmers.co.kr/learn/courses/30/lessons/17682) | 프로그래머스 | 점수/보너스/옵션 파싱, 10 처리 주의 |
| [72410 신규 아이디 추천](https://school.programmers.co.kr/learn/courses/30/lessons/72410) | 프로그래머스 | 7단계 치환을 순서대로 구현 |
| [81301 숫자 문자열과 영단어](https://school.programmers.co.kr/learn/courses/30/lessons/81301) | 프로그래머스 | 영단어 → 숫자 `replace` |
| [142086 가장 가까운 같은 글자](https://school.programmers.co.kr/learn/courses/30/lessons/142086) | 프로그래머스 | `lastIndexOf`로 코드 단순화 |
| [49993 스킬트리](https://school.programmers.co.kr/learn/courses/30/lessons/49993) | 프로그래머스 | 정규식 `replaceAll("[^...]", "")`로 필요한 글자만 추출 |
| 49072 1등과 2등 | 구름 | `"12"` / `"21"` 부분 문자열 포함 여부 |

## ⚫ 수학 · 정수론

| 문제 | 플랫폼 | 핵심 아이디어 |
| --- | --- | --- |
| [12953 N개의 최소공배수](https://school.programmers.co.kr/learn/courses/30/lessons/12953) | 프로그래머스 | 유클리드 호제법으로 GCD → LCM 누적 |
| [135807 숫자 카드 나누기](https://school.programmers.co.kr/learn/courses/30/lessons/135807) | 프로그래머스 | 각 배열의 GCD가 상대 배열을 나누지 못하는지 검사 |
| [12936 줄 서는 방법](https://school.programmers.co.kr/learn/courses/30/lessons/12936) | 프로그래머스 | 첫 자리는 `(n-1)!` 묶음 → 팩토리얼 진법 |
| [140107 점 찍기](https://school.programmers.co.kr/learn/courses/30/lessons/140107) | 프로그래머스 | 이중 반복문 **시간초과** → x마다 y 개수를 수식으로 |
| [12901 2016년](https://school.programmers.co.kr/learn/courses/30/lessons/12901) | 프로그래머스 | 누적 일수 % 7로 요일 계산 |

## 🔷 해시 탐색 — 중복 · 개수 · 짝 맞추기

| 문제 | 플랫폼 | 핵심 아이디어 |
| --- | --- | --- |
| [1845 폰켓몬](https://school.programmers.co.kr/learn/courses/30/lessons/1845) | 프로그래머스 | `HashSet` 종류 수와 `N/2` 중 작은 값 |
| [42578 의상](https://school.programmers.co.kr/learn/courses/30/lessons/42578) | 프로그래머스 | 종류별 개수 → `(개수+1)` 곱 - 1 |
| [42577 전화번호 목록](https://school.programmers.co.kr/learn/courses/30/lessons/42577) | 프로그래머스 | 정렬 후 인접한 두 개만 접두어 비교 |
| [131127 할인 행사](https://school.programmers.co.kr/learn/courses/30/lessons/131127) | 프로그래머스 | 원하는 목록 해시맵 vs 10일 구간 해시맵 비교 |
| 175909 카드 모으기 | 구름 | `HashSet`으로 종류 수집, 못 모으면 -1 |
| 159545 0커플 | 구름 | 합이 0인 짝을 제거하고 남은 사람 출력 |

## 🔶 스택 활용

| 문제 | 플랫폼 | 핵심 아이디어 |
| --- | --- | --- |
| [12973 짝지어 제거하기](https://school.programmers.co.kr/learn/courses/30/lessons/12973) | 프로그래머스 | top과 같으면 pop, 마지막에 비어야 성공 |
| [131704 택배 상자](https://school.programmers.co.kr/learn/courses/30/lessons/131704) | 프로그래머스 | 보조 컨테이너를 스택으로 시뮬레이션 |
| [154539 뒤에 있는 큰 수 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/154539) | 프로그래머스 | **단조 스택** — 인덱스를 쌓고 큰 수를 만나면 해결 |

---

# ② 자료구조별 정리

## 배열 / 2차원 배열 `int[][]`, `char[][]`, `boolean[][]`

> 격자 탐색의 기본. **맵 배열 + `visited` 배열 + `dx/dy` 방향 배열** 세트로 사용.
> 4방향은 `{0,0,-1,1} / {-1,1,0,0}`, 8방향은 대각선까지 8칸으로 확장(4963).

`1012` `2178` `2667` `7576` `14502` · `1520` `1926` `1987` `2468` `2583` `4963` `10026` · `42840` `43105` `86491`

## Queue `LinkedList` — BFS 전용

> `Queue<int[]> q = new LinkedList<>();` 로 좌표를 넣고 `poll()`로 한 겹씩 확장.
> **큐에 넣는 순간 방문 처리**해야 중복 삽입을 막을 수 있다는 점을 반복 학습.

`1012` `1260` `1697` `2178` `2606` `2667` `7576` `11724` `14502` · `154538`

## Deque `ArrayDeque`

> 앞/뒤 양쪽에서 넣고 빼야 할 때. LRU 캐시에서 **히트 시 맨 뒤로 이동, 초과 시 맨 앞 제거**.

`17680 [1차] 캐시`

## Stack

> "가장 최근 것부터 처리 / 되돌리기" 신호가 보이면 스택.

| 쓰임새 | 문제 |
| --- | --- |
| 짝 제거 | `12973 짝지어 제거하기` |
| 시뮬레이션 | `131704 택배 상자`, `347925 문서 편집기(undo)` |
| 단조 스택 | `154539 뒤에 있는 큰 수 찾기` |
| 그리디 + 스택 | `42883 큰 수 만들기` |

## PriorityQueue

> 매 순간 최댓값/최솟값을 꺼내야 할 때. `new PriorityQueue<>(Collections.reverseOrder())`로 최대 힙.

`142085 디펜스 게임` — 지나온 라운드 중 소모가 가장 큰 라운드에 무적권을 몰아준다.

## HashMap

> **개수 집계**와 **키 → 값 매핑**. `getOrDefault(key, 0) + 1` 패턴을 계속 사용.

`42578 의상` `131127 할인 행사` `138476 귤 고르기` `92341 주차 요금 계산`

## HashSet

> **중복 제거**와 **방문 여부**. 종류 수를 셀 때, BFS의 visited가 좌표가 아닌 값일 때.

`1845 폰켓몬` `154538 숫자 변환하기(visited)` `175909 카드 모으기`

## ArrayList

> 크기를 미리 모를 때(영역 넓이 목록, 인접 리스트). `ArrayList<Integer>[]`로 **그래프 인접 리스트** 구성.

- 인접 리스트: `1260` `2606` `11724` `1707` `2644` `11725` `163021`
- 결과 수집 후 정렬: `2667` `1926` `2468` `2583` `142085`

## 정렬 `Arrays.sort` / `Collections.sort`

> 정렬만 해도 절반이 풀리는 문제들. 내림차순은 `Collections.reverseOrder()`.

`12941 최솟값 만들기(오름 × 내림)` `42577 전화번호 목록(정렬 후 인접 비교)` `42747 H-Index`
`2667` `1926` `2468` `2583`(영역 크기 정렬) · `138476` `142085`(내림차순)

---

# ③ 폴더 구조

```
coding_test_study
├── baekjoon
│   ├── bfs   (9)   1012 1260 1697 2178 2606 2667 7576 11724 14502
│   ├── dfs  (10)   1520 1707 1926 1987 2468 2583 2644 4963 10026 11725
│   └── dp    (1)   2839
├── programmers (34)  programmers{문제번호}/Main.java
└── goorm        (6)  goorm{문제번호}/Main.java
```

# ④ 정리하면서 얻은 것

- **BFS와 DFS의 선택 기준**
  - 최단 거리 · 최소 횟수 → BFS (`1697`, `2178`, `7576`, `154538`)
  - 영역 / 연결 요소 세기 → DFS (`1926`, `2583`, `4963`, `163021`)
  - 둘 다 되는 문제(`1012`, `2606`)로 같은 문제를 두 방식으로 비교
- **탐색 + 다른 알고리즘의 결합**
  - `14502` 조합(백트래킹) + BFS
  - `1520` DFS + 메모이제이션 — 단순 재귀가 시간초과 나는 이유를 직접 확인
- **시간초과를 줄인 경험**: `140107`의 이중 반복문을 수식으로 대체
- **자료구조 선택 습관**
  - 중복 제거 → `HashSet` / 개수 집계 → `HashMap.getOrDefault`
  - 되돌리기·직전 값 비교 → `Stack` / 매 순간 최댓값 → `PriorityQueue`
  - 최단 거리 → `Queue`, 양쪽 접근 → `ArrayDeque`
