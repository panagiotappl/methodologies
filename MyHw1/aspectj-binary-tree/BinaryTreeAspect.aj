import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public aspect BinaryTreeAspect {
    final Map<BinaryTree, ReentrantReadWriteLock> locks = new ConcurrentHashMap<BinaryTree, ReentrantReadWriteLock>();
    //final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // CONTSTRUCTOR 
    pointcut binaryTree_Called(BinaryTree bt): initialization(BinaryTree.new(..)) && this(bt);

    before(BinaryTree bt): binaryTree_Called(bt) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        locks.put(bt, lock);
    }

    // INSERT or DELETE
    pointcut binaryTreeWrite_Called(BinaryTree bt) : (call(void BinaryTree.insert(int)) || call(void BinaryTree.remove(int)))  && target(bt);
    
    before(BinaryTree bt): binaryTreeWrite_Called(bt) {
        ReentrantReadWriteLock lock = locks.get(bt);
        lock.writeLock().lock();
    }

    after(BinaryTree bt) : binaryTreeWrite_Called(bt) {
        ReentrantReadWriteLock lock = locks.get(bt);
        lock.writeLock().unlock();
    }

    // LOOKUP
    pointcut binaryTreeLookUp_Called(BinaryTree bt) : call(Boolean BinaryTree.lookUp(int)) && target(bt);
    
    before(BinaryTree bt): binaryTreeLookUp_Called(bt) {
        ReentrantReadWriteLock lock = locks.get(bt);
        lock.readLock().lock();
    }

    after(BinaryTree bt) : binaryTreeLookUp_Called(bt) {
        ReentrantReadWriteLock lock = locks.get(bt);
        lock.readLock().unlock();
    }
}
