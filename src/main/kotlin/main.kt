fun main(args: Array<String>) {
    println(TreeChallenge.solution(intArrayOf(3, 4, 5, 3, 7))) // Should return 3
    println(TreeChallenge.solution(intArrayOf(1, 2, 3, 4, 2, 5))) // Should return -1
    println(TreeChallenge.solution(intArrayOf(1, 3, 1, 2))) // Should return 0
}

object TreeChallenge {
    fun solution(A: IntArray): Int {
        if(areAesthetic(A.toList())) return 0

        var possibilities = 0

        for(i in A.indices) {
            val data = A.toMutableList()
            data.removeAt(i)
            if(areAesthetic(data)) possibilities++
        }

        return if(possibilities == 0) -1 else possibilities
    }

    private fun areAesthetic(trees: List<Int>): Boolean {
        if(trees.count() in 0..1) return true

        var nextTreeNeedsToBe = TreeComparison.NOT_SET_YET

        for (i in 1 until trees.count()) {
            val previousTree = trees[i -1]
            val currentTree = trees[i]

            if(currentTree == previousTree) return false

            nextTreeNeedsToBe = when(nextTreeNeedsToBe) {
                TreeComparison.NOT_SET_YET -> {
                    if(currentTree > previousTree) TreeComparison.SMALLER else TreeComparison.TALLER
                }
                TreeComparison.TALLER -> {
                    if(currentTree < previousTree) return false
                    TreeComparison.SMALLER
                }
                TreeComparison.SMALLER -> {
                    if(currentTree > previousTree) return false
                    TreeComparison.TALLER
                }
            }
        }

        return true
    }
}

enum class TreeComparison {
    NOT_SET_YET, TALLER, SMALLER
}
