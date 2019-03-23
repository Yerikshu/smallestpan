package com.smpteam.utils.web.rest;

import com.smpteam.utils.UtilsApp;

import com.smpteam.utils.config.SecurityBeanOverrideConfiguration;

import com.smpteam.utils.domain.DownloadFile;
import com.smpteam.utils.repository.DownloadFileRepository;
import com.smpteam.utils.service.DownloadFileService;
import com.smpteam.utils.service.dto.DownloadFileDTO;
import com.smpteam.utils.service.mapper.DownloadFileMapper;
import com.smpteam.utils.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.smpteam.utils.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DownloadFileResource REST controller.
 *
 * @see DownloadFileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, UtilsApp.class})
public class DownloadFileResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOWNLOAD_TIME = "AAAAAAAAAA";
    private static final String UPDATED_DOWNLOAD_TIME = "BBBBBBBBBB";

    private static final Instant DEFAULT_COUNT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_COUNT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DownloadFileRepository downloadFileRepository;

    @Autowired
    private DownloadFileMapper downloadFileMapper;

    @Autowired
    private DownloadFileService downloadFileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDownloadFileMockMvc;

    private DownloadFile downloadFile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DownloadFileResource downloadFileResource = new DownloadFileResource(downloadFileService);
        this.restDownloadFileMockMvc = MockMvcBuilders.standaloneSetup(downloadFileResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DownloadFile createEntity() {
        DownloadFile downloadFile = new DownloadFile()
            .name(DEFAULT_NAME)
            .downloadTime(DEFAULT_DOWNLOAD_TIME)
            .count(DEFAULT_COUNT);
        return downloadFile;
    }

    @Before
    public void initTest() {
        downloadFileRepository.deleteAll();
        downloadFile = createEntity();
    }

    @Test
    public void createDownloadFile() throws Exception {
        int databaseSizeBeforeCreate = downloadFileRepository.findAll().size();

        // Create the DownloadFile
        DownloadFileDTO downloadFileDTO = downloadFileMapper.toDto(downloadFile);
        restDownloadFileMockMvc.perform(post("/api/download-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadFileDTO)))
            .andExpect(status().isCreated());

        // Validate the DownloadFile in the database
        List<DownloadFile> downloadFileList = downloadFileRepository.findAll();
        assertThat(downloadFileList).hasSize(databaseSizeBeforeCreate + 1);
        DownloadFile testDownloadFile = downloadFileList.get(downloadFileList.size() - 1);
        assertThat(testDownloadFile.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDownloadFile.getDownloadTime()).isEqualTo(DEFAULT_DOWNLOAD_TIME);
        assertThat(testDownloadFile.getCount()).isEqualTo(DEFAULT_COUNT);
    }

    @Test
    public void createDownloadFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = downloadFileRepository.findAll().size();

        // Create the DownloadFile with an existing ID
        downloadFile.setId("existing_id");
        DownloadFileDTO downloadFileDTO = downloadFileMapper.toDto(downloadFile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDownloadFileMockMvc.perform(post("/api/download-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadFileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DownloadFile in the database
        List<DownloadFile> downloadFileList = downloadFileRepository.findAll();
        assertThat(downloadFileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDownloadFiles() throws Exception {
        // Initialize the database
        downloadFileRepository.save(downloadFile);

        // Get all the downloadFileList
        restDownloadFileMockMvc.perform(get("/api/download-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(downloadFile.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].downloadTime").value(hasItem(DEFAULT_DOWNLOAD_TIME.toString())))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT.toString())));
    }
    
    @Test
    public void getDownloadFile() throws Exception {
        // Initialize the database
        downloadFileRepository.save(downloadFile);

        // Get the downloadFile
        restDownloadFileMockMvc.perform(get("/api/download-files/{id}", downloadFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(downloadFile.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.downloadTime").value(DEFAULT_DOWNLOAD_TIME.toString()))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT.toString()));
    }

    @Test
    public void getNonExistingDownloadFile() throws Exception {
        // Get the downloadFile
        restDownloadFileMockMvc.perform(get("/api/download-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDownloadFile() throws Exception {
        // Initialize the database
        downloadFileRepository.save(downloadFile);

        int databaseSizeBeforeUpdate = downloadFileRepository.findAll().size();

        // Update the downloadFile
        DownloadFile updatedDownloadFile = downloadFileRepository.findById(downloadFile.getId()).get();
        updatedDownloadFile
            .name(UPDATED_NAME)
            .downloadTime(UPDATED_DOWNLOAD_TIME)
            .count(UPDATED_COUNT);
        DownloadFileDTO downloadFileDTO = downloadFileMapper.toDto(updatedDownloadFile);

        restDownloadFileMockMvc.perform(put("/api/download-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadFileDTO)))
            .andExpect(status().isOk());

        // Validate the DownloadFile in the database
        List<DownloadFile> downloadFileList = downloadFileRepository.findAll();
        assertThat(downloadFileList).hasSize(databaseSizeBeforeUpdate);
        DownloadFile testDownloadFile = downloadFileList.get(downloadFileList.size() - 1);
        assertThat(testDownloadFile.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDownloadFile.getDownloadTime()).isEqualTo(UPDATED_DOWNLOAD_TIME);
        assertThat(testDownloadFile.getCount()).isEqualTo(UPDATED_COUNT);
    }

    @Test
    public void updateNonExistingDownloadFile() throws Exception {
        int databaseSizeBeforeUpdate = downloadFileRepository.findAll().size();

        // Create the DownloadFile
        DownloadFileDTO downloadFileDTO = downloadFileMapper.toDto(downloadFile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDownloadFileMockMvc.perform(put("/api/download-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadFileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DownloadFile in the database
        List<DownloadFile> downloadFileList = downloadFileRepository.findAll();
        assertThat(downloadFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDownloadFile() throws Exception {
        // Initialize the database
        downloadFileRepository.save(downloadFile);

        int databaseSizeBeforeDelete = downloadFileRepository.findAll().size();

        // Get the downloadFile
        restDownloadFileMockMvc.perform(delete("/api/download-files/{id}", downloadFile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DownloadFile> downloadFileList = downloadFileRepository.findAll();
        assertThat(downloadFileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DownloadFile.class);
        DownloadFile downloadFile1 = new DownloadFile();
        downloadFile1.setId("id1");
        DownloadFile downloadFile2 = new DownloadFile();
        downloadFile2.setId(downloadFile1.getId());
        assertThat(downloadFile1).isEqualTo(downloadFile2);
        downloadFile2.setId("id2");
        assertThat(downloadFile1).isNotEqualTo(downloadFile2);
        downloadFile1.setId(null);
        assertThat(downloadFile1).isNotEqualTo(downloadFile2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DownloadFileDTO.class);
        DownloadFileDTO downloadFileDTO1 = new DownloadFileDTO();
        downloadFileDTO1.setId("id1");
        DownloadFileDTO downloadFileDTO2 = new DownloadFileDTO();
        assertThat(downloadFileDTO1).isNotEqualTo(downloadFileDTO2);
        downloadFileDTO2.setId(downloadFileDTO1.getId());
        assertThat(downloadFileDTO1).isEqualTo(downloadFileDTO2);
        downloadFileDTO2.setId("id2");
        assertThat(downloadFileDTO1).isNotEqualTo(downloadFileDTO2);
        downloadFileDTO1.setId(null);
        assertThat(downloadFileDTO1).isNotEqualTo(downloadFileDTO2);
    }
}
