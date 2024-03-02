//task1
fun singleNumber(nums: IntArray): Int {
    var result = 0
    for (num in nums) {
        result = result xor num
    }
    return result
}
//task2
fun minSplit(amount: Int, coins: IntArray): Int {
    if (amount < 0) {
        return -1
    }
    var minCoins = 0
    var remainingAmount = amount

    for (coin in coins) {
        if (coin <= 0) {
            return -1
        }
        if (remainingAmount == 0) {
            break
        }
        val numCoins = remainingAmount / coin
        minCoins += numCoins
        remainingAmount %= coin
    }

    return if (remainingAmount == 0) {
        minCoins
    } else {
        -1
    }
}
//task3
fun longestPrefix(array: Array<String>): String {
    if (array.isEmpty()) {
        return ""
    }

    var prefix = array[0]

    for (i in 1..<array.size) {
        while (!array[i].startsWith(prefix)) {
            prefix = prefix.substring(0, prefix.length - 1)
            if (prefix.isEmpty()) {
                return ""
            }
        }
    }

    return prefix
}

//task4
fun addBinary(a: String, b: String): String {
    val result = StringBuilder()
    var carry = 0
    var i = a.length - 1
    var j = b.length - 1

    while (i >= 0 || j >= 0 || carry > 0) {
        var sum = carry
        if (i >= 0) {
            sum += a[i] - '0'
            i--
        }
        if (j >= 0) {
            sum += b[j] - '0'
            j--
        }
        result.insert(0, sum % 2)
        carry = sum / 2
    }
    return result.toString()
}

//task5
fun countVariants(stairsCount: Int): Int {
    if (stairsCount <= 1) {
        return 1
    }

    val ways = IntArray(stairsCount + 1)
    ways[0] = 1
    ways[1] = 1

    for (i in 2..stairsCount) {
        ways[i] = ways[i - 1] + ways[i - 2]
    }
    return ways[stairsCount]
}

fun main() {
    //task1
    val nums = intArrayOf(1, 2, 2, 1, 3, 3, 5, 12221, 12221, 99, 100, 88, 99, 88, 100)
    val result = singleNumber(nums)
    println("The number that does not repeat - $result")

    //task2
    val coins = intArrayOf(50, 20, 10, 5, 1)
    val amount = 0 //insert the number of coins here
    val minCoins = minSplit(amount, coins)

    if (minCoins != -1) {
        println("Minimum number of coins: $minCoins")
    } else {
        println("Impossible to calculate change with the given amount.")
    }

    //task3
    val words = arrayOf("extract", "exhale", "excavate")
    val prefix = longestPrefix(words)
    println("The longest common prefix is: $prefix")

    //task4
    val a = "1010"
    val b = "1011"
    val sum = addBinary(a, b)
    println("sum: $sum")

    //task5
    val stairsCount = 20
    val ways = countVariants(stairsCount)
    println("Number of stairs: $stairsCount\number of ways to climb the stairs: $ways")

    //task6
    val customHashSet = CustomHashSet<Int>()
    customHashSet.insert(1)
    customHashSet.insert(2)
    customHashSet.insert(3)

    println("CustomHashSet contains 2: " + customHashSet.contains(2))
    println("CustomHashSet size: " + customHashSet.getSize())

    customHashSet.delete(2)

    println("CustomHashSet contains 2: " + customHashSet.contains(2))
    println("CustomHashSet size: " + customHashSet.getSize())
}