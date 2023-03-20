package model;

/**
 * @Description: 线段树（动态开点）
 * 基于求「区间和」以及对区间进行「加减」的更新操作，且为「动态开点」
 **/
public class SegmentTreeDynamic {
    class Node {
        Node left, right;
        int val, add;
    }
    private int N = (int) 1e9;
    private Node root = new Node();

    // 在区间 [start, end] 中更新区间 [l, r] 的值，将区间 [l, r] ➕ val
    // 这是因为我们使用了「懒惰标记」的方法，我们只需要更新到满足条件的区间即可，
    // 然后再给该区间对应的节点加一个懒惰标记，表示该节点所有对应的孩子节点都应该有此更新
    public void update(Node node, int start, int end, int l, int r, int val) {
        // 找到满足要求的区间
        if (l <= start && end <= r) {
            // 区间节点加上更新值
            // 注意：需要✖️该子树所有叶子节点
            node.val += (end - start + 1) * val;
            // 添加懒惰标记
            // 对区间进行「加减」的更新操作，懒惰标记需要累加，不能直接覆盖
            node.add += val;
            return ;
        }
        int mid = (start + end) >> 1;
        // 下推标记
        // mid - start + 1：表示左孩子区间叶子节点数量
        // end - mid：表示右孩子区间叶子节点数量
        pushDown(node, mid - start + 1, end - mid);
        // [start, mid] 和 [l, r] 可能有交集，遍历左孩子区间
        if (l <= mid) update(node.left, start, mid, l, r, val);
        // [mid + 1, end] 和 [l, r] 可能有交集，遍历右孩子区间
        if (r > mid) update(node.right, mid + 1, end, l, r, val);
        // 向上更新
        pushUp(node);
    }
    // 在区间 [start, end] 中查询区间 [l, r] 的结果，即 [l ,r] 保持不变
// 对于上面的例子，应该这样调用该函数：query(root, 0, 4, 2, 4)
    public int query(Node node, int start, int end, int l, int r) {
        // 区间 [l ,r] 完全包含区间 [start, end]
        // 例如：[2, 4] = [2, 2] + [3, 4]，当 [start, end] = [2, 2] 或者 [start, end] = [3, 4]，直接返回
        if (l <= start && end <= r) return node.val;
        // 把当前区间 [start, end] 均分得到左右孩子的区间范围
        // node 左孩子区间 [start, mid]
        // node 左孩子区间 [mid + 1, end]
        int mid = (start + end) >> 1, ans = 0;
        // 下推标记
        pushDown(node, mid - start + 1, end - mid);
        // [start, mid] 和 [l, r] 可能有交集，遍历左孩子区间
        if (l <= mid) ans += query(node.left, start, mid, l, r);
        // [mid + 1, end] 和 [l, r] 可能有交集，遍历右孩子区间
        if (r > mid) ans += query(node.right, mid + 1, end, l, r);
        // ans 把左右子树的结果都累加起来了，与树的后续遍历同理
        return ans;
    }

    private void pushUp(Node node) {
        node.val = node.left.val + node.right.val;
    }
    private void pushDown(Node node, int leftNum, int rightNum) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return ;
        // 注意：当前节点加上标记值✖️该子树所有叶子节点的数量
        node.left.val += node.add * leftNum;
        node.right.val += node.add * rightNum;
        // 对区间进行「加减」的更新操作，下推懒惰标记时需要累加起来，不能直接覆盖
        node.left.add += node.add;
        node.right.add += node.add;
        node.add = 0;
    }
    //表示区间的最大，直接覆盖
//    class Node {
//        // 左右孩子节点
//        Node left, right;
//        // 当前节点值，以及懒惰标记的值
//        int val, add;
//    }
//    private int N = (int) 1e9;
//    private Node root = new Node();
//    public void update(Node node, int start, int end, int l, int r, int val) {
//        if (l <= start && end <= r) {
//            node.val = val;
//            node.add = val;
//            return ;
//        }
//        pushDown(node);
//        int mid = (start + end) >> 1;
//        if (l <= mid) update(node.left, start, mid, l, r, val);
//        if (r > mid) update(node.right, mid + 1, end, l, r, val);
//        pushUp(node);
//    }
//    public int query(Node node, int start, int end, int l, int r) {
//        if (l <= start && end <= r) return node.val;
//        pushDown(node);
//        int mid = (start + end) >> 1, ans = 0;
//        if (l <= mid) ans = query(node.left, start, mid, l, r);
//        if (r > mid) ans = Math.max(ans, query(node.right, mid + 1, end, l, r));
//        return ans;
//    }
//    private void pushUp(Node node) {
//        // 每个节点存的是当前区间的最大值
//        node.val = Math.max(node.left.val, node.right.val);
//    }
//    private void pushDown(Node node) {
//        if (node.left == null) node.left = new Node();
//        if (node.right == null) node.right = new Node();
//        if (node.add == 0) return ;
//        node.left.val = node.add;
//        node.right.val = node.add;
//        node.left.add = node.add;
//        node.right.add = node.add;
//        node.add = 0;
//    }

}
