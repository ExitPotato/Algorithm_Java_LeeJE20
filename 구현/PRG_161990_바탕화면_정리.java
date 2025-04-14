// 레벨1, 14분 소요, 1트에 성공

class Solution {
    public int[] solution(String[] wallpaper) {
        
        int maxH = -1, maxW = -1;
        int minH= Integer.MAX_VALUE, minW = Integer.MAX_VALUE; // height, width
        
        for (int h = 0; h < wallpaper.length; h++) {
            char[] row = wallpaper[h].toCharArray();
            for (int w = 0; w < row.length; w++) {
                if (row[w] == '.') {
                    continue;
                }
                if (minH == Integer.MAX_VALUE) {
                    minH = h;
                }
                maxH = Math.max(maxH, h);
                maxW = Math.max(maxW, w);
                if (minW != 0) {
                    minW = Math.min(minW, w);
                }
                
            }
        }
        int[] answer = {minH, minW, maxH + 1, maxW + 1};
        return answer;
    }
}
