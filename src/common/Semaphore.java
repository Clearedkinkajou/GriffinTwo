package common;

/**
 * Class Semaphore
 * Implements artificial semaphore built on top of Java's sync primitives.
 *
 * $Revision: 1.4 $
 * $Last Revision Date: 2019/02/02 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca; Inspired by previous code by Prof. D. Probst
 */
public class Semaphore
{
	/**
	 * Current semaphore's value
	 */
	private int iValue;

	/*
	 * ------------
	 * Constructors
	 * ------------
	 */

	/**
	 * With value parameter.
	 *
	 * NOTE: There is no check made whether the value is positive or negative.
	 * This implementation allows initialization of a semaphore to a negative
	 * value because it's the only way it can become so. Wait() does not do
	 * that for us. The semantic of that could be that how many threads are
	 * _anticipated_ to be waiting on that semaphore.
	 *
	 * @param piValue Initial value of the semaphore to set.
	 */
	public Semaphore(int piValue)
	{
		this.iValue = piValue;
	}

	/**
	 * Default. Equivalent to Semaphore(0)
	 */
	public Semaphore()
	{
		this(0);
	}

	/**
	 * Returns true if locking condition is true.
	 * Usually used in PA3-4.
	 */
	public synchronized boolean isLocked()
	{
		return (this.iValue <= 0);
	}

	/*
	 * -----------------------------
	 * Standard semaphore operations
	 * -----------------------------
	 */

	/**
	 * Puts thread asleep if semamphore's values is less than or equal to zero.
	 *
	 * NOTE: This implementation as-is does not allow semaphore's value
	 * to become negative.
	 */
	public synchronized void Wait()
	{
		try
		{
			while(this.iValue <= 0)
			{
				wait();
			}

			this.iValue--;
		}
		catch(InterruptedException e)
		{
			System.out.println
			(
				"Semaphore::Wait() - caught InterruptedException: " +
				e.getMessage()
			);

			e.printStackTrace();
		}
	}

	/**
	 * Increments semaphore's value and notifies another (single) thread of the change.
	 *
	 * NOTES: By waking up any arbitrary thread using notify() and not notify
	 * all are simply being a little more efficient: we don't really care which
	 * thread is this, any would do just fine.
	 */
	public synchronized void Signal()
	{
		++this.iValue;
		notify();
	}

	/**
	 * Proberen. An alias for Wait().
	 */
	public synchronized void P()
	{
		this.Wait();
	}

	/**
	 * Verhogen. An alias for Signal()
	 */
	public synchronized void V()
	{
		this.Signal();
	}
}

// EOF
