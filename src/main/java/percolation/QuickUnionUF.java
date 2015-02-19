package percolation;

/**
 * User: vovkvant
 * Date: 03.02.15
 */
public class QuickUnionUF {

    private int[] id;
    private int[] sz;

    public QuickUnionUF(int N){
        id = new int[N];
        for(int i=0; i<N; i++){
            id[i] = i;
        }
        sz = new int[N];
        for(int i=0; i<N; i++){
            sz[i]=1;
        }
    }

    public int root(int i){
        while(i!=id[i]){
            //id[i] = id[id[i]];
            i=id[i];
        }
        return i;
    }

    public void union(int p, int q){
        int i = id[p];
        int j = id[q];
        for(int k=0; k<id.length; k++){
            if(id[k] == i){
                id[k] = j;
            }
        }
    }

    public void union2(int p, int q){
        int i = root(p);
        int j = root(q);
        if(i==j) return;
        if(sz[i] < sz[j]){
            id[i]=j; sz[j]+=sz[i];
        }else{
            id[j]=i; sz[i]+=sz[j];
        }

    }

    public void print(){
        for(int i=0; i<id.length;i++){
            System.out.print(id[i]+" ");
        }
    }

    public static void main(String[] args){
        System.out.println("Hello World!");

        //0-7 0-9 9-6 5-2 5-3 1-4
        int[][] in={{0, 7}, {7, 9}, {9, 6}, {5, 2} ,{5, 3}, {1, 4}};

        QuickUnionUF uf = new QuickUnionUF(10);
        for(int i=0; i<in.length; i++){
            uf.union(in[i][0], in[i][1]);
        }
        uf.print();
        System.out.print("\n");

        //3-5 1-6 6-2 4-8 6-7 4-5 0-7 7-5 8-9
        int[][] in2={{3, 5}, {1, 6}, {6, 2}, {4, 8} ,{6, 7}, {4, 5}, {0, 7}, {7, 5}, {8, 9}};
        QuickUnionUF uf2 = new QuickUnionUF(10);
        for(int i=0; i<in2.length; i++){
            uf2.union2(in2[i][0], in2[i][1]);
        }
        uf2.print();

    }
}
