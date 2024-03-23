package cn.catguild.common.type;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @param <T> 子节点类对象
 * @param <R> 主键ID类型
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
		if (CollectionUtils.isEmpty(allNode)) {
			return new ArrayList<>();
		}
		List<U> forestRoots = new ArrayList<>();

		for (U node : allNode) {
			boolean isTop = false;
			if (node.getParentId() instanceof Long) {
				isTop = ObjectUtils.nullSafeEquals(node.getParentId(), 0L);
			}
			if (node.getParentId() instanceof Integer) {
				isTop = ObjectUtils.nullSafeEquals(node.getParentId(), 0);
			}
			if (isTop || ObjectUtils.nullSafeEquals(node.getParentId(), 0)) {
				// 根节点
				forestRoots.add(node);
				node.setChildren(collectChildren(node, allNode));
			}
		}
		return forestRoots;
	}

	static <U extends CatTreeNode<U, Y>, Y, T extends CatTreeNode<T, Y>> List<T> copy(Collection<U> sourceNode, Class<T> targetClass) {
		if (CollectionUtils.isEmpty(sourceNode)) {
			return new ArrayList<>();
		}
		return (List<T>) convertChildren(sourceNode, targetClass);
	}

	private static <U extends CatTreeNode<U, Y>, Y, T extends CatTreeNode<T, Y>> Collection<T> convertChildren(Collection<U> sourceNode, Class<T> targetClass) {
		Collection<T> newTreeNode = new ArrayList<>();
		for (U menu : sourceNode) {
			T menuVO = convert(menu, targetClass);
			newTreeNode.add(menuVO);
		}
		return newTreeNode;
	}

	private static <U extends CatTreeNode<U, Y>, Y, T extends CatTreeNode<T, Y>> T convert(U menu, Class<T> targetClass) {
		try {
			T node = targetClass.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(menu, node);
			// 递归转换子菜单
			node.setChildren(convertChildren(menu.getChildren(), targetClass));
			return node;
		} catch (InvocationTargetException | InstantiationException | IllegalAccessException |
				 NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
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
			if (ObjectUtils.nullSafeEquals(node.getParentId(), rootNode.getId())) {
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
