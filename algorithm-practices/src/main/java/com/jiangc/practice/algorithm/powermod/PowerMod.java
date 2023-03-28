package com.jiangc.practice.algorithm.powermod;

/**
 * 类名称：PowerMod<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class PowerMod {

    /**
     *
     * 次方求模
     * 时间限制：1000 ms | 内存限制：65535 KB
     * 难度：3
     *
     * 描述
     * 求a的b次方对c取余的值
     *
     * 输入
     * 第一行输入一个整数n表示测试数据的组数（n<100）
     *  每组测试只有一行，其中有三个正整a,b,c(1<a,b,c<=1000000000)
     *
     *
     *  输出：
     * 输出a的b次方对c取余之后的结果
     *
     *
     *
     *
     */
    public static void main(String[] args) {
        //System.out.println(Arrays.asList(args));//输入为空

        //定义二维数组
        //int[][]  arr = {{5,7,9},{12,14,16,18},{23,25,36,47},{22,54,65,15},{22,34}};
        //int sum=add(arr);//调用求和函数
        //System.out.println("sum="+sum);//输出和


        //int [][] a = new int[3][3];

        //int n = 3;
        int [][] arr = {{2,3,5},{3,100,10},{11,12345,12345}};

        /**
         * 3
         *     1
         *     10481
         */
        for (int i = 0; i <= arr.length - 1; i++) {
            int ans = powerMod(arr[i][0],arr[i][1],arr[i][2]);
            System.out.println(i + "------>" + ans);
        }


    }


    /**
     * 0------>3
     * 1------>-5
     * 2------>5972
     */
    public static int solution1(int a, int b, int c){
        int ans = 1;
        for (int i = 1; i <= b; i++) {
            ans*=a;
        }
        ans%=c;
        return ans;
    }


    /**
     * a^b mod c=(a mod c)^b
     *
     * 引理：
     *
     * (a * b) mod c =[ ( a mod c )* (b mod c) ] mod c ;
     *
     * 证明： 设 a mod c =d，b mod c= e;
     *
     * 则：a=t*c + d ;  b=k*c + e ;
     *
     *    (a*b)mod c = (t*c+d)(k*c+e) mod c
     *
     *              = (tk c^2 + ( te+dk ) *c + d*e) mod c
     *
     *              =de mod c
     *
     * 即积的取余等于取余的积的取余.
     *
     * (a ^ b)mod c 由上述公式迭代即可得到 ( a mod c)^b.
     *
     * 证明了以上的公式以后，我们可以先让a关于c取余，这样可以大大减少a的大小，于是不用思考的进行了改进：
     *
     *
     * 0------>3
     * 1------>-5
     * 2------>5972
     *
     */
    public static int solution2(int a,int b,int c){
        int ans = 1,i;
        a = a % c;//加上这一句
        for (i = 1; i <= b; i++ ) {
            ans = ans * a;
        }
        ans = ans % c;
        return ans;
    }

    /**
     *
     *
     * 既然某个因子取余之后相乘再取余保持余数不变，那么新算得的ans也可以进行取余，所以得到比较良好的改进版本。
     *
     * 0------>3
     * 1------>1
     * 2------>10481
     * 时间复杂度上没有改进，仍为O(b)
     *
     */
    public static int solution3(int a,int b,int c){
        int ans = 1,i;
        a = a % c;//加上这一句
        for (i = 1; i <= b; i++ ) {
            ans = (ans * a) % c;
        }
        ans = ans % c;
        return ans;
    }


    /**
     * a^b mod c = ((a^2)^(b/2)) mod c ,b 是偶数
     * a^b mod c = ((a^2)^(b/2) * a) mod c,b是奇数
     *
     * 有了上述两个公式后，我们可以得出以下的结论：
     * 1，如果b是偶数，我们可以记 k = a^2 mod c,那么求 (k)^(b/2) mod c
     * 2，如果b是奇数，我们可以记 k = a^2 mod c,那么求 ((k)^(b/2) mod c * a) mod c = ((k)^(b/2) mod c * a) mod c
     *
     * 可以得到如下算法
     *
     * 0------>3
     * 1------>1
     * 2------>10481
     *
     * 时间复杂度变成了O(b/2)
     *
     */
    public static int solution4(int a,int b,int c){
        int ans = 1,i;
        a = a % c;
        if ( b % 2 == 1) {
            ans = (ans * a) % c;//如果是奇数，要多求一步， 可以提前算到 ans 中。
        }
        int k = (a * a) % c; //我们取a^2 而不是a
        for (i = 1; i <= b/2; i++) {
            ans = (ans * k) % c;
        }
        ans = ans % c;
        return ans;
    }


    /**
     *
     *
     * 当我们令k = (a * a) mod c时，状态已经发生了变化，我们所要求的最终结果即为 k^(b/2) mod c
     *
     * 而不是原来的a^b mod c，所以我们发现这个过程是可以迭代下去的。当然，对于奇数的情形会多出一项a mod c，所以为了完成迭代，当b是奇数时，我们通过 ans = (ans * a) % c;
     *
     * 来弥补多出来的这一项，此时剩余的部分就可以进行迭代了。
     *
     * 形如上式的迭代下去后，当b=0时，所有的因子都已经相乘，算法结束。
     *
     * 于是便可以在O（log b）的时间内完成了。
     *
     * 于是，有了最终的算法：快速幂算法。
     *
     * 本算法的时间复杂度为O（logb），能在几乎所有的程序设计（竞赛）过程中通过，是目前最常用的算法之一。
     *
     *
     */
    public static int powerMod(int a,int b,int c){
        int ans = 1;
        a = a % c;
        while (b > 0){
            if (b % 2 == 1){
                ans = (ans * a) % c;
            }
            b = b/2; // b>>1;
            a = (a * a) % c;
        }
        return ans;
    }



    public static int add(int[][] arr)//求和子函数
    {
        int sum=0;
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[i].length;j++)//利用嵌套for循环来遍历二维数组
            {
                sum+=arr[i][j];//每遍历出一个元素则叠加一次
            }
        }
        return sum;//返回二维数组中个元素的和
    }
}
