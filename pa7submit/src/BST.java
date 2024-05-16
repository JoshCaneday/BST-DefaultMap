import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {
	/* 
	 * TODO: Add instance variables 
	 * You may add any instance variables you need, but 
	 * you may NOT use any class that implements java.util.SortedMap
	 * or any other implementation of a binary search tree
	 */
	int size;
	public Node root;
	List<K> keys;
	
	public BST()
	{
		size = 0;
		keys = new ArrayList<>();
	}
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		if (root == null)
		{
			root = new Node(key, value, null, null);
			size +=1;
			return true;
		}
		Node temp = root;
		
		while(temp != null)
		{	
			if (temp.getKey().equals(key))
			{
				return false;
			}
			else if (temp.getKey().compareTo(key) < 0)
			{
				if (temp.getRight() != null)
				{
					temp = temp.getRight();
				}
				else
				{
					temp.setRight(new Node(key,value,null,null));
					size+=1;
					return true;
				}
				
			}
			else
			{
				if (temp.getLeft() != null)
				{
					temp = temp.getLeft();
				}
				else
				{
					temp.setLeft(new Node(key,value,null,null));
					size+=1;
					return true;
				}
				
			}
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		Node temp = root;
		
		while(temp != null)
		{	
			if (temp.getKey().equals(key))
			{
				temp.setValue(newValue);
				return true;
			}
			else if (temp.getKey().compareTo(key) < 0)
			{
				temp = temp.getRight();
			}
			else
			{
				temp = temp.getLeft();
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		//this needs some work
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		Node temp = root;
		Node otherTemp = root.getRight();
		Node lastTemp;
		Node placeHolder = null;
		
		while(temp != null)
		{	
			if (temp.getKey().equals(key))
			{
				if (temp.getKey().equals(root.getKey()))
				{
					if (temp.getRight() == null && temp.getLeft() == null)
					{
						root = null;
						size -= 1;
						return true;
					}
					else if (temp.getRight() != null && temp.getLeft() == null)
					{
						root = temp.getRight();
						size -= 1;
						return true;
					}
					else if (temp.getRight() == null && temp.getLeft() != null)
					{
						root = temp.getLeft();
						size -= 1;
						return true;
					}
					else
					{
						while (otherTemp.getLeft() != null)
						{
							if (otherTemp.getLeft().getLeft() == null)
							{
								temp = otherTemp;
							}
							otherTemp = otherTemp.getLeft();
						}
						if (otherTemp.getRight() != null)
						{
							lastTemp = otherTemp.getRight();
						}
						else
						{
							lastTemp = null;
						}
						otherTemp.setLeft(root.getLeft());
						otherTemp.setRight(root.getRight());
						root = otherTemp;
						temp.setLeft(lastTemp);
						size -=1;
						return true;
						
					}
					
				}
			}
			else if (temp.getKey().compareTo(key) < 0)
			{
				if (temp.getRight() == null)
				{
					return false;
				}
				if (temp.getRight().getKey().equals(key))
				{
					if (temp.getRight().getRight() == null && temp.getRight().getLeft() == null)
					{
						temp.setRight(null);
						size-=1;
						return true;
					}
					else if (temp.getRight().getRight() != null && temp.getRight().getLeft() == null)
					{
						temp.setRight(temp.getRight().getRight());
						size-=1;
						return true;
					}
					else if (temp.getRight().getRight() == null && temp.getRight().getLeft() != null)
					{
						temp.setRight(temp.getRight().getLeft());
						size-=1;
						return true;
					}
					else if (temp.getRight().getRight() != null && temp.getRight().getLeft() != null)
					{
						otherTemp = temp.getRight().getRight();
						while (otherTemp.getLeft() != null)
						{
							if (otherTemp.getLeft().getLeft() == null)
							{
								placeHolder = otherTemp;
							}
							otherTemp = otherTemp.getLeft();
						}
						if (otherTemp.getRight() != null)
						{
							lastTemp = otherTemp.getRight();
						}
						else
						{
							lastTemp = null;
						}
						otherTemp.setLeft(temp.getRight().getLeft());
						otherTemp.setRight(temp.getRight().getRight());
						temp.setRight(otherTemp);
						if (placeHolder == null)
						{
							placeHolder = temp.getRight().getRight();
						}
						placeHolder.setLeft(lastTemp);
						size -=1;
						return true;
					}
					
				}
				else
				{
					temp = temp.getRight();
				}
				
			}
			else if (temp.getKey().compareTo(key) > 0)
			{
				if (temp.getLeft() == null)
				{
					return false;
				}
				if (temp.getLeft().getKey().equals(key))
				{
					if (temp.getLeft().getRight() == null && temp.getLeft().getLeft() == null)
					{
						temp.setLeft(null);
						size-=1;
						return true;
					}
					else if (temp.getLeft().getRight() != null && temp.getLeft().getLeft() == null)
					{
						temp.setLeft(temp.getLeft().getRight());
						size-=1;
						return true;
					}
					else if (temp.getLeft().getRight() == null && temp.getLeft().getLeft() != null)
					{
						temp.setLeft(temp.getLeft().getLeft());
						size-=1;
						return true;
					}
					else if (temp.getLeft().getRight() != null && temp.getLeft().getLeft() != null)
					{
						otherTemp = temp.getLeft().getRight();
						while (otherTemp.getLeft() != null)
						{
							if (otherTemp.getLeft().getLeft() == null)
							{
								placeHolder = otherTemp;
							}
							otherTemp = otherTemp.getLeft();
						}
						if (otherTemp.getRight() != null)
						{
							lastTemp = otherTemp.getRight();
						}
						else
						{
							lastTemp = null;
						}
						otherTemp.setLeft(temp.getLeft().getLeft());
						otherTemp.setRight(temp.getLeft().getRight());
						temp.setLeft(otherTemp);
						if (placeHolder == null)
						{
							placeHolder = temp.getLeft().getRight();
						}
						placeHolder.setLeft(lastTemp);
						size -=1;
						return true;
					}
				}
				else
				{
					temp = temp.getLeft();
				}
			}
			
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		if (root == null)
		{
			root = new Node(key, value, null, null);
			size +=1;
			return;
		}
		Node temp = root;
		
		while(temp != null)
		{	
			if (temp.getKey().equals(key))
			{
				temp.setValue(value);
				return;
			}
			else if (temp.getKey().compareTo(key) < 0)
			{
				if (temp.getRight() != null)
				{
					temp = temp.getRight();
				}
				else
				{
					
					temp.setRight(new Node(key,value,null,null));
					size+=1;
					
					return;
				}
				
			}
			else
			{
				if (temp.getLeft() != null)
				{
					temp = temp.getLeft();
				}
				else
				{
					temp.setLeft(new Node(key,value,null,null));
					size+=1;
					
					return;
				}
				
			}
		}
		
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		Node temp = root;
		
		while(temp != null)
		{	
			if (temp.getKey().equals(key))
			{
				return (V) temp.getValue();
			}
			else if (temp.getKey().compareTo(key) < 0)
			{
				temp = temp.getRight();
			}
			else
			{
				temp = temp.getLeft();
			}
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (this.size == 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		Node temp = root;
		
		while(temp != null)
		{	
			if (temp.getKey().equals(key))
			{
				return true;
			}
			else if (temp.getKey().compareTo(key) < 0)
			{
				temp = temp.getRight();
			}
			else
			{
				temp = temp.getLeft();
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	// Keys must be in ascending sorted order
	// You CANNOT use Collections.sort() or any other sorting implementations
	// You must do inorder traversal of the tree
	@Override
	public List<K> keys() {
		this.keys = new ArrayList<>();
		Node temp = root;
		gatherKeys(root);
		// TODO Auto-generated method stub
		return this.keys;
	}
	
	public void gatherKeys(Node node)
	{
		if (node == null)
		{
			return;
		}
		gatherKeys(node.getLeft());
		this.keys.add((K) node.getKey());
		gatherKeys(node.getRight());
		
	}
	
	private static class Node<K extends Comparable<? super K>, V> implements DefaultMap.Entry<K, V> {
		/* 
		 * TODO: Add instance variables
		 */
		K key;
		V value;
		Node<K,V> left;
		Node<K,V> right;
		
		public Node(K key, V value, Node left, Node right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			
		}
		
		public void setRight(Node r)
		{
			this.right = r;
		}
		public void setLeft(Node l)
		{
			this.left = l;
		}
		public Node getRight()
		{
			return this.right;
		}
		public Node getLeft()
		{
			return this.left;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public void setValue(V value) {
			// TODO Auto-generated method stub
			this.value = value;
			
		}
		
		
	}
	 
}