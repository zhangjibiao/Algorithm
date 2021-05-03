import java.util.*;

public class Q22 {
    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void Node(int value, Node left, Node right){
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    public static List<Node> findDistanceOfK(Node root, Node target, int K){
        //入参规范判读
        if (null == root || target == null || K < 0){
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();
        generatePar(root, map);
        map.put(root, null);
        Queue<Node> queue = new LinkedList<>();
        List<Node> ans = new ArrayList<>();
        HashSet<Node> vistied = new HashSet<>();
        int distance = 0;
        queue.offer(target);
        vistied.add(target);
        while(!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){ // 将同一距离的节点遍历
                Node cur = queue.poll();
                if( distance == K){
                    ans.add(cur);
                }
                if(cur.left != null && !vistied.contains(cur.left)){
                    queue.add(cur.left);
                    vistied.add(cur.left);
                }
                if (cur.right != null && !vistied.contains(cur.right)){
                    queue.add(cur.right);
                    vistied.add(cur.right);
                }
                Node parent = map.get(cur);
                if (parent != null && !vistied.contains(parent)){
                    queue.add(parent);
                    vistied.add(parent);
                }
            }
            distance++;
            if(distance > K){ //接下来的节点的距离都超过K，代表得到答案
                break;
            }
        }
    return ans;
    }

    private static void generatePar(Node root, HashMap<Node, Node> map) {
        if (root.left != null){
            map.put(root.left, root);
            generatePar(root.left, map);
        }
        if (root.right != null){
            map.put(root.right, root);
            generatePar(root.right, map);
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = findDistanceOfK(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

    }
}
