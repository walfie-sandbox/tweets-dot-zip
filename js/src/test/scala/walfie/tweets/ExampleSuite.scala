package walfie.tweets

import utest._
import utest.ExecutionContext.RunNow

object ExampleSuite extends TestSuite {
  def tests = TestSuite {
    "1 + 1 should equal 2" - {
      assert(1 + 1 == 2)
    }
  }
}

