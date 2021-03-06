package com.future.round2;

import com.future.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
 in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization
 algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to
 the original tree structure.

 For example, you may serialize the following tree

       1
      / \
     2   3
        / \
       4   5
 as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow
 this format, so please be creative and come up with different approaches yourself.



 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 * Created by xingfeiy on 3/23/18.
 */
public class Problem297 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append("n").append("/");
            return;
        }

        sb.append(Integer.toString(node.val)).append("/");
        preOrder(node.left, sb);
        preOrder(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() < 1) return null;
        Queue<String> queue = new LinkedList<>();
        for(String str : data.split("/")) {
            if(str != null && str.length() > 0) queue.offer(str);
        }
        return helper(queue);
    }

    private TreeNode helper(Queue<String> queue) {
        if(queue.isEmpty()) return null;
        String str = queue.poll();
        if(str.equals("n")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = helper(queue);
        root.right = helper(queue);
        return root;
    }
}
