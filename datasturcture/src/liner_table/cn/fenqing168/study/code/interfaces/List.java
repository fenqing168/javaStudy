package liner_table.cn.fenqing168.study.code.interfaces;
/**
 * 线性表接口
 * 和存储结构无关
 * @author Administrator
 *
 */
public interface List {
	/**
	 * 返回线性表的大小，即数据元素的个数。
	 */
	int size();

	/**
	 * 返回线性表中序号为 i 的数据元素
	 * @param i  元素的索引
	 * @return   元素
	 */
	Object get(int i);

	/**
	 * 如果线性表为空返回 true，否则返回 false。
	 * @return   结果
	 */
	boolean isEmpty();

	/**
	 * 判断线性表是否包含数据元素 e
	 * @param e  元素
	 * @return   结果
	 */
	boolean contains(Object e);

	/**
	 * 返回数据元素 e 在线性表中的序号
	 * @param e    元素
	 * @return     索引
	 */
	int indexOf(Object e);

	/**
	 * 将数据元素 e 插入到线性表中 i 号位置
	 * @param i    目标索引
	 * @param e    元素
	 */
	void add(int i, Object e);

	/**
	 * 将数据元素 e 插入到线性表末尾
	 * @param e   元素
	 */
	void add(Object e);

	/**
	 * 将数据元素 e 插入到元素 obj 之前
	 * @param obj   元素
	 * @param e     源元素
	 * @return
	 */
	boolean addBefore(Object obj, Object e);

	/**
	 * 将数据元素 e 插入到元素 obj 之后
	 * @param obj  元素
	 * @param e    元元素
	 * @return
	 */
	boolean addAfter(Object obj, Object e);

	/**
	 * 删除线性表中序号为 i 的元素,并返回之
	 * @param i   索引
	 * @return    被删除的元素
	 */
	Object remove(int i);

	/**
	 * 删除线性表中第一个与 e 相同的元素
	 * @param e   元素
	 * @return    是否删除
	 */
	boolean remove(Object e);

	/**
	 * 替换线性表中序号为 i 的数据元素为 e，返回原数据元素
	 * @param i   索引
	 * @param e   元素
	 * @return
	 */
	Object replace(int i, Object e);


}
