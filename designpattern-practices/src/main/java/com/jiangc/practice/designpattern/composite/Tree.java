package com.jiangc.practice.designpattern.composite;

/**
 *
 * 组合模式
 *
 * 只要是树形结构，就考虑使用组合模式。
 *
 */
public class Tree {

	TreeNode root = null;
	
	public Tree(String name){
		root = new TreeNode(name);
	}
	
	public static void main(String[] args) {
		Tree tree = new Tree("A");
		TreeNode nodeB = new TreeNode("B");
		TreeNode nodeC = new TreeNode("C");
		
		nodeB.add(nodeC);
		tree.root.add(nodeB);
		System.out.println("Build the tree finished!");
		
	}
}
