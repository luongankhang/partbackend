package com.api.module.image.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface IStorageService {

    public String storeFile(MultipartFile file);

    public List<String> storeMFiles(List<MultipartFile> files);

    public Stream<Path> loadAll();

    public byte[] readFileContent(String fileName);

    public void deleteAllFiles();
}
