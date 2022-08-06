package cn.catguild.common.type;

import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xiyan
 * @date 2022-04-25 17:44
 */
public interface CatTreeNode<T, R> {

	/**
	 * list对象合并成森林
	 *
	 * @param allNode 节点平铺列表
	 * @param <U>     节点数据类型参数
	 * @return 森林
	 */
	static <U extends CatTreeNode<U, Y>, Y> List<U> merge(List<U> allNode) {
		List<U> forestRoots = new ArrayList<>();

		for (U node : allNode) {
			boolean isTop = false;
			if (node.getParentId() instanceof Long) {
				isTop = ObjectUtil.equals(node.getParentId(), 0L);
			}
			if (node.getParentId() instanceof Integer) {
				isTop = ObjectUtil.equals(node.getParentId(), 0);
			}
			if (isTop || ObjectUtil.equals(node.getParentId(), 0)) {
				// 根节点
				forestRoots.add(node);
				node.setChildren(collectChildren(node, allNode));
			}
		}
		return forestRoots;
	}

	/**
	 * 递归收集孩子节点
	 *
	 * @param rootNode 根节点
	 * @param allNode  节点平铺列表
	 * @param <U>      节点数据类型参数
	 * @return 孩子节点集合
	 */
	private static <U extends CatTreeNode<U, ?>> List<U> collectChildren(U rootNode, List<U> allNode) {
		List<U> children = new ArrayList<>();
		for (U node : allNode) {
			if (ObjectUtil.equals(node.getParentId(), rootNode.getId())) {
				children.add(node);
				node.setChildren(collectChildren(node, allNode));
			}
		}
		return children;
	}

	/**
	 * 获取主键id
	 *
	 * @return 主键id
	 */
	R getId();

	/**
	 * 获取父节点id
	 *
	 * @return 父节点id
	 */
	R getParentId();

	/**
	 * 获取孩子节点集合
	 *
	 * @return 子节点集合
	 */
	Collection<T> getChildren();

	/**
	 * 放入孩子节点集合
	 *
	 * @param children 子节点集合
	 */
	void setChildren(Collection<T> children);

}
