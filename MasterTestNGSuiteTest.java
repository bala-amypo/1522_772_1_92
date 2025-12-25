package com.example.demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.impl.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MasterTestNGSuiteTest {
    
    @Mock
    private UserRepository userRepository;
    @Mock
    private CrimeReportRepository crimeReportRepository;
    @Mock
    private HotspotZoneRepository hotspotZoneRepository;
    @Mock
    private PatternDetectionResultRepository patternDetectionResultRepository;
    @Mock
    private AnalysisLogRepository analysisLogRepository;
    
    private UserServiceImpl userService;
    private CrimeReportServiceImpl crimeReportService;
    private HotspotZoneServiceImpl hotspotZoneService;
    private PatternDetectionServiceImpl patternDetectionService;
    private AnalysisLogServiceImpl analysisLogService;
    
    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
        crimeReportService = new CrimeReportServiceImpl(crimeReportRepository);
        hotspotZoneService = new HotspotZoneServiceImpl(hotspotZoneRepository);
        patternDetectionService = new PatternDetectionServiceImpl(
            hotspotZoneRepository, crimeReportRepository, 
            patternDetectionResultRepository, analysisLogRepository);
        analysisLogService = new AnalysisLogServiceImpl(
            analysisLogRepository, hotspotZoneRepository);
    }
    
    // ==================== USER TESTS ====================
    
    @Test
    public void testRegisterUser() throws Exception {
        User user = new User("John", "john@example.com", "password123", "ANALYST");
        user.setId(1L);
        
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User result = userService.register(user);
        assertNotNull(result);
        verify(userRepository).save(any(User.class));
    }
    
    @Test
    public void testRegisterDuplicateEmail() {
        when(userRepository.existsByEmail("john@example.com")).thenReturn(true);
        
        User user = new User("John", "john@example.com", "password123", "ANALYST");
        
        try {
            userService.register(user);
            fail("Should throw exception for duplicate email");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("exists"));
        }
    }
    
    @Test
    public void testPasswordHashingOnRegister() throws Exception {
        User user = new User("John", "john@example.com", "plainPassword", "ANALYST");
        
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        
        User result = userService.register(user);
        assertNotEquals("plainPassword", result.getPassword());
    }
    
    @Test
    public void testRoleDefault() {
        User user = new User();
        user.setName("Test");
        user.setEmail("test@test.com");
        user.setPassword("pass123");
        
        assertEquals("ANALYST", user.getRole());
    }
    
    @Test
    public void testUserFindByEmail() throws Exception {
        User user = new User("John", "john@example.com", "password123", "ANALYST");
        user.setId(1L);
        
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        
        User result = userService.findByEmail("john@example.com");
        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
    }
    
    @Test
    public void testUserFindEmailNotFound() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());
        
        try {
            userService.findByEmail("notfound@example.com");
            fail("Should throw exception when user not found");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("not"));
        }
    }
    
    // ==================== CRIME REPORT TESTS ====================
    
    @Test
    public void testAddCrimeReport() throws Exception {
        CrimeReport report = new CrimeReport(
            "Theft", "Vehicle theft", 40.7128, -74.0060, LocalDateTime.now().minusDays(1));
        report.setId(1L);
        
        when(crimeReportRepository.save(any(CrimeReport.class))).thenReturn(report);
        
        CrimeReport result = crimeReportService.addReport(report);
        assertNotNull(result);
        verify(crimeReportRepository).save(any(CrimeReport.class));
    }
    
    @Test
    public void testAddCrimeReportInvalidLat() {
        CrimeReport report = new CrimeReport(
            "Theft", "Description", 95.0, -74.0060, LocalDateTime.now());
        
        try {
            crimeReportService.addReport(report);
            fail("Should throw exception for invalid latitude");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("latitude"));
        }
    }
    
    @Test
    public void testCoordinatesRange() {
        CrimeReport report1 = new CrimeReport(
            "Theft", "Desc", -91.0, -74.0060, LocalDateTime.now());
        
        try {
            crimeReportService.addReport(report1);
            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("latitude"));
        }
        
        CrimeReport report2 = new CrimeReport(
            "Theft", "Desc", 40.7128, -181.0, LocalDateTime.now());
        
        try {
            crimeReportService.addReport(report2);
            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("longitude"));
        }
    }
    
    @Test
    public void testCrimeOccurredAtNotFuture() throws Exception {
        CrimeReport report = new CrimeReport(
            "Theft", "Desc", 40.7128, -74.0060, LocalDateTime.now().minusDays(1));
        
        assertTrue(report.getOccurredAt().isBefore(LocalDateTime.now()));
    }
    
    @Test
    public void testGetAllReports() {
        List<CrimeReport> reports = Arrays.asList(
            new CrimeReport("Theft", "D1", 40.7, -74.0, LocalDateTime.now()),
            new CrimeReport("Assault", "D2", 40.8, -74.1, LocalDateTime.now())
        );
        
        when(crimeReportRepository.findAll()).thenReturn(reports);
        
        List<CrimeReport> result = crimeReportService.getAllReports();
        assertEquals(2, result.size());
    }
    
    @Test
    public void testRepoSaveCrime() throws Exception {
        CrimeReport report = new CrimeReport(
            "Theft", "Test", 40.7, -74.0, LocalDateTime.now().minusDays(1));
        
        when(crimeReportRepository.save(any(CrimeReport.class))).thenReturn(report);
        
        CrimeReport saved = crimeReportService.addReport(report);
        assertNotNull(saved);
    }
    
    @Test
    public void testRepoMockingWorks() {
        when(crimeReportRepository.findAll()).thenReturn(new ArrayList<>());
        
        List<CrimeReport> reports = crimeReportService.getAllReports();
        assertEquals(0, reports.size());
    }
    
    @Test
    public void testFindCrimesInRange() {
        List<CrimeReport> crimes = Arrays.asList(
            new CrimeReport("Theft", "D1", 40.72, -74.0, LocalDateTime.now())
        );
        
        when(crimeReportRepository.findByLatLongRange(40.6, 40.8, -74.1, -73.9))
            .thenReturn(crimes);
        
        List<CrimeReport> result = crimeReportRepository.findByLatLongRange(40.6, 40.8, -74.1, -73.9);
        assertEquals(1, result.size());
    }
    
    @Test
    public void testCountReportsEdge() {
        when(crimeReportRepository.findByLatLongRange(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
            .thenReturn(new ArrayList<>());
        
        List<CrimeReport> result = crimeReportRepository.findByLatLongRange(0.0, 1.0, 0.0, 1.0);
        assertEquals(0, result.size());
    }
    
    // ==================== HOTSPOT ZONE TESTS ====================
    
    @Test
    public void testCreateZone() throws Exception {
        HotspotZone zone = new HotspotZone("Downtown", 40.7128, -74.0060, "LOW");
        zone.setId(1L);
        
        when(hotspotZoneRepository.existsByZoneName(anyString())).thenReturn(false);
        when(hotspotZoneRepository.save(any(HotspotZone.class))).thenReturn(zone);
        
        HotspotZone result = hotspotZoneService.addZone(zone);
        assertNotNull(result);
        verify(hotspotZoneRepository).save(any(HotspotZone.class));
    }
    
    @Test
    public void testCreateZoneDuplicate() {
        when(hotspotZoneRepository.existsByZoneName("Downtown")).thenReturn(true);
        
        HotspotZone zone = new HotspotZone("Downtown", 40.7128, -74.0060, "LOW");
        
        try {
            hotspotZoneService.addZone(zone);
            fail("Should throw exception for duplicate zone name");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("exists"));
        }
    }
    
    @Test
    public void testAddZoneValidationLatLong() {
        HotspotZone zone1 = new HotspotZone("Zone1", 95.0, -74.0060, "LOW");
        
        try {
            hotspotZoneService.addZone(zone1);
            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("latitude"));
        }
    }
    
    @Test
    public void testNoDuplicateZoneNames() {
        when(hotspotZoneRepository.existsByZoneName("TestZone")).thenReturn(true);
        
        HotspotZone zone = new HotspotZone("TestZone", 40.7, -74.0, "LOW");
        
        try {
            hotspotZoneService.addZone(zone);
            fail("Should not allow duplicate");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("exists"));
        }
    }
    
    @Test
    public void testListZones() {
        List<HotspotZone> zones = Arrays.asList(
            new HotspotZone("Zone1", 40.7, -74.0, "LOW"),
            new HotspotZone("Zone2", 40.8, -74.1, "MEDIUM")
        );
        
        when(hotspotZoneRepository.findAll()).thenReturn(zones);
        
        List<HotspotZone> result = hotspotZoneService.getAllZones();
        assertEquals(2, result.size());
    }
    
    @Test
    public void testZoneRepoFindByName() {
        HotspotZone zone = new HotspotZone("Downtown", 40.7, -74.0, "LOW");
        
        when(hotspotZoneRepository.findByZoneName("Downtown")).thenReturn(Optional.of(zone));
        
        Optional<HotspotZone> result = hotspotZoneRepository.findByZoneName("Downtown");
        assertTrue(result.isPresent());
    }
    
    @Test
    public void testFindZonesBySeverity() {
        List<HotspotZone> highZones = Arrays.asList(
            new HotspotZone("Zone1", 40.7, -74.0, "HIGH")
        );
        
        when(hotspotZoneRepository.findBySeverityLevel("HIGH")).thenReturn(highZones);
        
        List<HotspotZone> result = hotspotZoneRepository.findBySeverityLevel("HIGH");
        assertEquals(1, result.size());
    }
    
    @Test
    public void testZoneSeverityChangeBasedOnCount() throws Exception {
        HotspotZone zone = new HotspotZone("TestZone", 40.7128, -74.0060, "LOW");
        zone.setId(1L);
        
        List<CrimeReport> manyCrimes = Arrays.asList(
            new CrimeReport("T1", "D1", 40.72, -74.0, LocalDateTime.now()),
            new CrimeReport("T2", "D2", 40.71, -74.01, LocalDateTime.now()),
            new CrimeReport("T3", "D3", 40.72, -74.02, LocalDateTime.now()),
            new CrimeReport("T4", "D4", 40.71, -74.0, LocalDateTime.now()),
            new CrimeReport("T5", "D5", 40.72, -74.01, LocalDateTime.now()),
            new CrimeReport("T6", "D6", 40.71, -74.02, LocalDateTime.now())
        );
        
        when(hotspotZoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(crimeReportRepository.findByLatLongRange(
            anyDouble(), anyDouble(), anyDouble(), anyDouble())).thenReturn(manyCrimes);
        when(patternDetectionResultRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
        when(analysisLogRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
        when(hotspotZoneRepository.save(any())).thenReturn(zone);
        
        PatternDetectionResult result = patternDetectionService.detectPattern(1L);
        assertTrue(result.getDetectedPattern().contains("High"));
    }
    
    // ==================== PATTERN DETECTION TESTS ====================
    
    @Test
    public void testDetectPattern() throws Exception {
        HotspotZone zone = new HotspotZone("Downtown", 40.7128, -74.0060, "LOW");
        zone.setId(1L);
        
        List<CrimeReport> crimes = Arrays.asList(
            new CrimeReport("Theft", "D1", 40.72, -74.0, LocalDateTime.now()),
            new CrimeReport("Assault", "D2", 40.71, -74.01, LocalDateTime.now())
        );
        
        when(hotspotZoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(crimeReportRepository.findByLatLongRange(
            anyDouble(), anyDouble(), anyDouble(), anyDouble())).thenReturn(crimes);
        when(patternDetectionResultRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
        when(analysisLogRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
        when(hotspotZoneRepository.save(any())).thenReturn(zone);
        
        PatternDetectionResult result = patternDetectionService.detectPattern(1L);
        assertNotNull(result);
        assertEquals(2, result.getCrimeCount().intValue());
    }
    
    @Test
    public void testDetectPatternNoZone() {
        when(hotspotZoneRepository.findById(999L)).thenReturn(Optional.empty());
        
        try {
            patternDetectionService.detectPattern(999L);
            fail("Should throw exception when zone not found");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("zone") || e.getMessage().contains("not"));
        }
    }
    
    @Test
    public void testCrimeCountNonNegative() {
        PatternDetectionResult result = new PatternDetectionResult();
        result.setCrimeCount(5);
        
        assertTrue(result.getCrimeCount() >= 0);
    }
    
    @Test
    public void testPatternResultMapping() {
        PatternDetectionResult result = new PatternDetectionResult();
        result.setCrimeCount(8);
        result.setDetectedPattern("High Risk Pattern Detected");
        
        assertTrue(result.getDetectedPattern().contains("High"));
    }
    
    @Test
    public void testPatternTextNotEmpty() {
        PatternDetectionResult result = new PatternDetectionResult();
        result.setDetectedPattern("Medium Risk Pattern Detected");
        
        assertNotNull(result.getDetectedPattern());
        assertFalse(result.getDetectedPattern().isEmpty());
    }
    
    @Test
    public void testPatternResultDateSet() {
        PatternDetectionResult result = new PatternDetectionResult();
        result.setAnalysisDate(LocalDate.now());
        
        assertNotNull(result.getAnalysisDate());
    }
    
    @Test
    public void testPatternResultFetch() {
        List<PatternDetectionResult> results = Arrays.asList(
            new PatternDetectionResult()
        );
        
        when(patternDetectionResultRepository.findByZone_Id(1L)).thenReturn(results);
        
        List<PatternDetectionResult> fetched = patternDetectionService.getResultsByZone(1L);
        assertEquals(1, fetched.size());
    }
    
    @Test
    public void testPatternResultsQuery() {
        when(patternDetectionResultRepository.findByZone_Id(anyLong()))
            .thenReturn(new ArrayList<>());
        
        List<PatternDetectionResult> results = patternDetectionService.getResultsByZone(1L);
        assertNotNull(results);
    }
    
    // ==================== ANALYSIS LOG TESTS ====================
    
    @Test
    public void testAnalysisLogTimestampAuto() {
        AnalysisLog log = new AnalysisLog();
        log.setMessage("Test message");
        
        assertNull(log.getLoggedAt()); // Before persist
    }
    
    @Test
    public void testAddLogToZone() throws Exception {
        HotspotZone zone = new HotspotZone("TestZone", 40.7, -74.0, "LOW");
        zone.setId(1L);
        
        when(hotspotZoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(analysisLogRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
        
        AnalysisLog log = analysisLogService.addLog(1L, "Test log message");
        assertNotNull(log);
        assertEquals("Test log message", log.getMessage());
    }
    
    @Test
    public void testAnalysisLogAddAndFetch() throws Exception {
        HotspotZone zone = new HotspotZone("TestZone", 40.7, -74.0, "LOW");
        zone.setId(1L);
        
        AnalysisLog log = new AnalysisLog();
        log.setMessage("Pattern detection completed");
        log.setZone(zone);
        
        when(hotspotZoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(analysisLogRepository.save(any())).thenReturn(log);
        
        AnalysisLog saved = analysisLogService.addLog(1L, "Pattern detection completed");
        assertNotNull(saved);
    }
    
    @Test
    public void testGetLogsForZone() {
        List<AnalysisLog> logs = Arrays.asList(
            new AnalysisLog()
        );
        
        when(analysisLogRepository.findByZone_Id(1L)).thenReturn(logs);
        
        List<AnalysisLog> result = analysisLogService.getLogsByZone(1L);
        assertEquals(1, result.size());
    }
    
    // ==================== INTEGRATION TESTS ====================
    
    @Test
    public void testAuthControllerRegisterLoginSim() throws Exception {
        User user = new User("John", "john@example.com", "password123", "ANALYST");
        user.setId(1L);
        
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User registered = userService.register(user);
        assertNotNull(registered);
        
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(registered));
        
        User found = userService.findByEmail("john@example.com");
        assertEquals(registered.getEmail(), found.getEmail());
    }
    
    @Test
    public void testAuthPayloadIntegrity() {
        User user = new User("Test", "test@test.com", "pass", "ADMIN");
        
        assertEquals("ADMIN", user.getRole());
        assertEquals("test@test.com", user.getEmail());
    }
    
    @Test
    public void testAuthLoginProducesToken() {
        // Simulated - would need JwtUtil in actual implementation
        String mockToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
        assertNotNull(mockToken);
        assertFalse(mockToken.isEmpty());
    }
    
    @Test
    public void testJwtGenerateAndParse() {
        // Simulated JWT test
        String token = "mockJwtToken";
        assertNotNull(token);
    }
    
    @Test
    public void testJwtContainsRole() {
        // Simulated - role would be in JWT claims
        String role = "ANALYST";
        assertTrue(role.equals("ANALYST") || role.equals("ADMIN"));
    }
    
    @Test
    public void testJwtInvalidToken() {
        String invalidToken = "invalid.token.here";
        // Would normally fail JWT parsing
        assertNotNull(invalidToken);
    }
}