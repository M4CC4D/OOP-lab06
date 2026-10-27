package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {
    
    private Map<N, Set<N>> map = new HashMap<>();

    public void addNode(N node) {
        if(map.containsKey(node)) {
            return;
        }
        map.put(node,new HashSet<>());
    }

    public void addEdge(N source, N target) {
        assert(source != null);
        assert(target != null);
        map.get(source).add(target);
    }

    public Set<N> nodeSet() {
        return new HashSet<>(map.keySet());
    }

    public Set<N> linkedNodes(N node) {
        if(map.containsKey(node)) {
            return new HashSet<>(map.get(node));
        }
        return Collections.emptySet();
    }

    public List<N> getPathDFS(N source, N target) {
        if (!map.containsKey(source) || !map.containsKey(target)) {
            return Collections.emptyList();
        }
        
        List<Set<N>> dfsQueue = new ArrayList<>();
        Set<N> last = new HashSet<>(Set.of(source));


        while(!containedInAny(dfsQueue, target)) {
            final Set<N> next = new HashSet<>();
            for(final N node : last) {
                next.addAll(notContainedIn(dfsQueue, linkedNodes(node)));
            }
            if(next.isEmpty()) {
                return Collections.emptyList();
            }
            dfsQueue.add(last);
            last = next;
        }


        final List<N> path = new ArrayList<>();
        path.add(target);

        for(final Set<N> level : dfsQueue.subList(0, dfsQueue.size() - 1).reversed()) {
            path.addFirst(predecessorIn(level, path.getFirst()));
        }
        return path;
    }


    private N predecessorIn(final Set<N> level, final N node) {
        for(final N candidate : level) {
            if(linkedNodes(candidate).contains(node)) {
                return candidate;
            }
        }
        return null;
    }

    private Set<N> notContainedIn(final List<Set<N>> levels, final Set<N> s) {
        Set<N> ret = new HashSet<>();
        for(final N node : s) {
            if(!containedInAny(levels, node)) {
                ret.add(node);
            }
        }
        return ret;
    }

    private boolean containedInAny(final List<Set<N>> levels, final N node) {
        for(final Set<N> set : levels) {
            if(set.contains(node)) {
                return true;
            }
        }
        return false;
    }

    public List<N> getPathBFS(final N source, final N target) {
        if (!map.containsKey(source) || !map.containsKey(target)) {
            return Collections.emptyList();
        }
        return BFS(source, target, new HashSet<N>());
    }

    private List<N> BFS(final N source, final N target, final Set<N> queue) {
        if(target.equals(source)) {
            return new ArrayList<>(List.of(target));
        }
        List<N> path;
        queue.add(source);
        for(final N node : linkedNodes(source)) {
            path = BFS(node, target, queue);
            if(path != null) {
                path.addFirst(source);
                return path;
            }
        }

        return null;
    }
}