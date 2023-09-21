package edu.ilstu;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<E> implements Iterable<E> {
	//inner Node class
	private static class Node<E> {
		private E data;
		private Node<E> next;
		
		private Node(E data) {
			this.data = data;
		}
		
		private Node(E data, Node<E> node) {
			this.data = data;
			next = node;
		}
	}
	
	// instance variables for the linked list class
	private Node<E> head;
	private int size;
	
	public void addFirst(E data) {
		//1. make a new node
	//	Node<E> newNode = new Node<>(data);
		
		//2. make the newNode point to the current head
	//	newNode.next = head;
		
		//3. make head point to newNode
	//	head = newNode;
		
		head = new Node<>(data, head); // Does above 3 steps in one line
		
		//4. increase the size
		size++;
	}
	
	private Node<E> getNode(int index) {
		Node<E> tempRef = head;
		for(int i=0; i<index; i++) {
			tempRef = tempRef.next;
		}
		return tempRef;
			
	}
	
	private void addAfter(Node<E> node, E data) { 
		node.next = new Node<>(data, node.next); 
		size++;
	}
	
	//To do: add(int, E)
	public void add(int index, E data) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException(Integer.toString(index));
		if(index == 0)
			addFirst(data);
		else {
			Node<E> priorNode = getNode(index-1);
			addAfter(priorNode, data);
		}
	}
	
	//append
	public boolean add(E data) {
		add(size, data);
		return true;
	}
	
	public E get(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException(Integer.toString(index));
		Node<E> theNode = getNode(index);
		return theNode.data;
	}
	
	public E set(int index, E data) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException(Integer.toString(index));
		Node<E> theNode = getNode(index);
		E returned = theNode.data;
		theNode.data = data;
		return returned;
	}
	
	public int size() {
		return size;
	}
	
	public int indexOf(E target) {
		//1. check if the list is empty
		if(head == null)
			return -1;
		else {
			Node<E> nodeRef = head;
			
			for(int i=0; i<size; i++) {
				if(nodeRef.data.equals(target))
					return i;
				nodeRef = nodeRef.next;
			}
			
		//	int i=0;                   2ND WAY
		//	while(nodeRef != null) {
		//		if(nodeRef.data.equals(target))
		//			return i;
		//		nodeRef = nodeRef.next;
		//		i++;
		//	}
			
		}
		return -1;
	}
	
	private E removeFirst() {
		// check if the lsit is empty
		if(head == null) 
			return null;
		else {
			E oldData = head.data;
			head = head.next;
			size--;
			return oldData;
		}
	}
	
	private E removeAfter(Node<E> node) {
		if(node.next == null)
			return null;
		else {
			E oldData = node.next.data;
			node.next = node.next.next;
			size--;
			return oldData;
		}
	}
	
	public E remove(int index) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException(Integer.toString(index));
		else if(index == 0)
			return removeFirst();
		else {
			Node<E> priorNode = getNode(index-1);
			return removeAfter(priorNode);
		}
	}
	
	public String toString() {
		String output = "[";
	//	for(int i=0; i<size; ++i) {
	//		output += get(i);
	//		if(i<size-1)
	//			output += ", ";
	//	}
		Iterator<E> iter = iterator();
		int counter = 0;
		while(iter.hasNext()) {
			output += iter.next();
			if(counter < size-1)
				output += ", ";
			++counter;
		}
		
		return output + "]";
	}
	
	//----iterator----//
	public Iterator<E> iterator() {
		return new Iter();
	}
	
	private class Iter implements Iterator<E> {
		private Node<E> prevNode;
		private Node<E> seenNode;
		private Node<E> nextNode;
		
		private Iter() {
			nextNode = head;
		}
		
		public boolean hasNext() {
			return nextNode != null;
		}
		
		public E next() {
			if(hasNext()) {
				prevNode = seenNode;
				seenNode = nextNode;
				nextNode = nextNode.next;
				return seenNode.data;
			}else
				throw new NoSuchElementException();
		}
		
		public void remove() {
			if(seenNode == null)
				throw new IllegalStateException();
			if(seenNode == head) {
				head  = head.next;
			}else {
				prevNode.next = nextNode;
			}
			seenNode = null;
			--size;
		}
	}
	
	
}
