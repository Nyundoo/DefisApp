package com.Defis;

public class MedicalRepositoryTest {
//	
//		@Autowired
//		private MedicalRepository repo;
//	
//	
//
//		@Test
//		public void testCreateNewMedical() {	
//			
//			Medical medicalRavi = new Medical(null, null, "1", "Check and passed", "ngara" ,0, null, false, "medical hosptital", false, null, null, null, null, false, null, null, null);
//			
//			Medical savedMedical = repo.save(medicalRavi);
//			
//			assertThat(savedMedical.getId()).isGreaterThan(0);
//		}
//	
//		@Test
//		public void testListAllMedicals() {
//			Iterable<Medical> listMedicals = repo.findAll();
//			listMedicals.forEach(medical -> System.out.print(medical));
//			
//		}
//		
//		@Test
//		public void testMedicalsById() {
//			
//			Medical medicalName = repo.findById(1).get();
//			System.out.print(medicalName);
//			assertThat(medicalName).isNotNull();
//		}
//	
//		
//		
//		@Test
//		public void testDeleteMedicals() {
//			Integer medicalId = 1;
//			repo.deleteById(medicalId);
//		}
//		
//	
//		
//	
//		@Test
//		public void testListFirstPage() {
//			int pageNumber = 0;
//			int pageSize = 1;
//			
//			PageRequest pageable = PageRequest.of(pageNumber, pageSize);
//			Page<Medical> page = repo.findAll(pageable);
//			
//			List<Medical> listMedicals = page.getContent();
//			
//			listMedicals.forEach(medical -> System.out.println(medical));
//			
//			assertThat(listMedicals.size()).isEqualTo(pageSize);
//		}
//		
//		@Test
//		public void testListSearchMedicals() {
//			String keyword = "Two";
//			
//			int pageNumber = 0;
//			int pageSize = 4;
//			
//			Pageable pageable = PageRequest.of(pageNumber, pageSize);
//			Page<Medical> page = repo.findAll(keyword, pageable);
//			
//			List<Medical> listMedicals = page.getContent();
//			
//			listMedicals.forEach(medical -> System.out.println(medical));
//			
//			assertThat(listMedicals.size()).isGreaterThan(0);
//		}
	}


