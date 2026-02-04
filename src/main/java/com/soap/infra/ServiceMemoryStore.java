package com.soap.infra;

import com.soap.domain.ServiceRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class ServiceMemoryStore {
    private final ConcurrentMap<String, ServiceRecord> store = new ConcurrentHashMap<>();

    public Optional<ServiceRecord> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public boolean deleteById(String id) {
        return store.remove(id) != null;
    }

    public boolean saveIfAbsent(ServiceRecord entity) {
        return store.putIfAbsent(entity.getServiceId(), entity) == null;
    }

    public boolean updateIfExists(ServiceRecord entity) {
        return store.replace(entity.getServiceId(), entity) != null;
    }
}
