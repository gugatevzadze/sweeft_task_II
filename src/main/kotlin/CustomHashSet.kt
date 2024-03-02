class CustomHashSet<T> {
    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR_THRESHOLD = 0.75
    }

    private var buckets: Array<MutableList<T>> = Array(INITIAL_CAPACITY) { mutableListOf() }
    private var size = 0

    private fun calculateHash(item: T): Int {
        return item.hashCode() % buckets.size
    }

    fun insert(item: T) {
        if (size.toDouble() / buckets.size >= LOAD_FACTOR_THRESHOLD) {
            resizeTable()
        }
        val index = calculateHash(item)
        val bucket = buckets[index]
        if (!bucket.contains(item)) {
            bucket.add(item)
            size++
        }
    }

    fun delete(item: T) {
        val index = calculateHash(item)
        val bucket = buckets[index]
        if (bucket.contains(item)) {
            bucket.remove(item)
            size--
        }
    }

    fun contains(item: T): Boolean {
        val index = calculateHash(item)
        val bucket = buckets[index]
        return bucket.contains(item)
    }

    fun getSize(): Int {
        return size
    }

    private fun resizeTable() {
        val newCapacity = buckets.size * 2
        val newBuckets = Array(newCapacity) { mutableListOf<T>() }
        for (bucket in buckets) {
            for (item in bucket) {
                val newIndex = calculateHash(item)
                newBuckets[newIndex].add(item)
            }
        }
        buckets = newBuckets
    }
}