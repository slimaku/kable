package kable

/**
 * A table is almost like a [Map] but, has two keys instead of one : the row and the column.
 */
interface Table<R, C, out V> {

    /** Number of entries in the table */
    val size: Int

    /** Rows that have at least one entry in the table */
    val rows: Set<R>

    /** Columns that have at least one entry in the table */
    val columns: Set<C>

    /** Values contained in the table */
    val values: Collection<V>

    /** Entries of the table */
    val entries: Set<Entry<R, C, V>>

    /** Return true if, and only if, the table has no entry */
    fun isEmpty(): Boolean = size == 0

    /** Return true if, and only if, the table has at least one entry for the row */
    fun containsRow(row: R): Boolean = row in rows

    /** Return true if, and only if, the table has at least one entry fot the column */
    fun containsColumn(column: C): Boolean = column in columns

    /** Return true if, and only if, the table contains the given value */
    fun containsValue(value: @UnsafeVariance V): Boolean = value in values

    /** Return true if, and only if, the table contains a value for the given row-column pair */
    fun contains(row: R, column: C): Boolean = get(row, column) != null

    /** Return all the values of the row mapped by column */
    fun getRow(row: R): Map<C, V>

    /** Return all the values of the columns mapped by row */
    fun getColumn(column: C): Map<R, V>

    /** Return the value at the specified row and column, or null if there is no value at this row-column pair */
    operator fun get(row: R, column: C): V?

    /** Return an iterator over the entries */
    operator fun iterator(): Iterator<Entry<R, C, V>> = entries.iterator()

    /** Table entry */
    interface Entry<out R, out C, out V> {

        /** Row */
        val row: R

        /** Column */
        val column: C

        /** Value */
        val value: V

        /** Row */
        operator fun component1() = row

        /** Column */
        operator fun component2() = column

        /** Value */
        operator fun component3() = value
    }
}