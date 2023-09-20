package base;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Solve{
    int x;
    int y;
    int z;
    public Solve(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Solve [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", z=");
		builder.append(z);
		builder.append("]");
		return builder.toString();
	}
    
}
public class 상범빌딩 {
    static int l;
    static int r;
    static int c;
    static char[][][] arr;
    static int[] dl = {-1, 1, 0, 0, 0, 0}; //상하북남서동
    static int[] dr = {0, 0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 0, -1, 1};
    static boolean[][][] visit;
    static int[][][] result;
    static int check;
    public static void main(String[] args)throws IOException {
    	String input = "3 4 5\r\n" + 
    			"S....\r\n" + 
    			".###.\r\n" + 
    			".##..\r\n" + 
    			"###.#\r\n" + 
    			"\r\n" + 
    			"#####\r\n" + 
    			"#####\r\n" + 
    			"##.##\r\n" + 
    			"##...\r\n" + 
    			"\r\n" + 
    			"#####\r\n" + 
    			"#####\r\n" + 
    			"#.###\r\n" + 
    			"####E\r\n" + 
    			"\r\n" + 
    			"0 0 0";

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        while(true){
            check=0;
            StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        r =Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        if(l==0 && r == 0 && c==0){
            break;
        }
        arr = new char[l][r][c];
        visit = new boolean[l][r][c];
        result = new int[l][r][c];
        for(int i=0; i<l; i++){
            for(int j=0; j<r; j++){
                String str = br.readLine();
                for(int k=0; k<c; k++){
                    arr[i][j][k] = str.charAt(k);
                }
            }
            br.readLine();
        }

        int x=0;
        int y = 0;
        int z = 0;

        for(int i=0; i<l; i++){
            for(int j=0; j<r; j++){
                for(int k=0; k<c; k++){
                    if(arr[i][j][k] == 'S'){
                        x = i;
                        y = j;
                        z = k;
                        break;
                    }
                }
            }
        }
        bfs(x,y,z);
        if(check==0){
            System.out.println("Trapped!");
        }
    }
}
public static void bfs(int x, int y, int z){
    Queue<Solve> q = new LinkedList<>();
    visit[x][y][z] = true;
    q.add(new Solve(x, y, z));
    
    while(!q.isEmpty()){
        Solve current = q.poll();
        if(arr[current.x][current.y][current.z] == 'E'){
            System.out.println("Escaped in " + result[current.x][current.y][current.z]+" minute(s).");
            check =1;
            return;
        }
        for(int i=0; i<6; i++){
            int nl = current.x + dl[i];
            int nr = current.y + dr[i];
            int nc = current.z + dc[i];
            if(nl>=0 && nr >=0 && nc >=0 && nl<l && nr<r && nc < c && !visit[nl][nr][nc] && arr[nl][nr][nc] != '#'){
                q.add(new Solve(nl, nr, nc));
                visit[nl][nr][nc] = true;
                result[nl][nr][nc] = result[current.x][current.y][current.z]+1;
                System.out.println(q);
            }
        }
    }
}
}