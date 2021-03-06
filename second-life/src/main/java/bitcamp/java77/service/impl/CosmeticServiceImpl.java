package bitcamp.java77.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bitcamp.java77.dao.CosmeticDao;
import bitcamp.java77.domain.AjaxResult;
import bitcamp.java77.domain.CosmeticCounsel;
import bitcamp.java77.domain.CosmeticCounselPhoto;
import bitcamp.java77.domain.CosmeticEvent;
import bitcamp.java77.domain.CosmeticHospital;
import bitcamp.java77.domain.CosmeticMember;
import bitcamp.java77.domain.CosmeticQnA;
import bitcamp.java77.domain.CosmeticReview;
import bitcamp.java77.domain.CosmeticReviewComment;
import bitcamp.java77.domain.CosmeticReviewPhoto;
import bitcamp.java77.domain.CosmeticReviewRecom;
import bitcamp.java77.domain.CosmeticSearch;
import bitcamp.java77.domain.CosmeticSugeryInfo;
import bitcamp.java77.domain.CosmeticWish;
import bitcamp.java77.domain.CosmeticWishEvent;
import bitcamp.java77.service.CosmeticService;

@Service
public class CosmeticServiceImpl implements CosmeticService {
	
	@Autowired
	CosmeticDao cosmeticDao;
	 
	@Override
	@Transactional
	public int insertReview(CosmeticReview cosmeticReview) throws Exception {
		return cosmeticDao.insertReview(cosmeticReview);
	}

	@Override
	@Transactional
	public void insertReviewPhoto(CosmeticReviewPhoto cosmeticReviewPhoto) throws Exception {
		cosmeticDao.insertReviewPhoto(cosmeticReviewPhoto);
	}

	@Override
	public void deleteSugeryInfo(int sugeryNo) throws Exception {
		cosmeticDao.deleteSugeryInfo(sugeryNo);
		
	}

	@Override
	@Transactional
	public CosmeticReview selectReviewListDetail(int reviewNo) throws Exception {
		cosmeticDao.updateReviewViewCnt(reviewNo);
		return cosmeticDao.selectReviewListDetail(reviewNo);
	}

	@Override
	@Transactional
	public HashMap<String, Object> selectReviewComment(CosmeticSearch search) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		result.put("comment", cosmeticDao.selectReviewComment(search));
		result.put("cnt", cosmeticDao.selectReviewCommentCount(search.getReviewNo()));
		return result;
	}

	@Override
	@Transactional
	public List<CosmeticReviewPhoto> selectReviewPhoto(int reviewNo) throws Exception {
		return cosmeticDao.selectReviewPhoto(reviewNo);
	}

	@Override
	@Transactional
	public int searchID(String id) throws Exception {
		return cosmeticDao.searchID(id);
	}

	@Override
	@Transactional
	public void insertMember(CosmeticMember cosmeticMember) throws Exception {
		cosmeticDao.insertMember(cosmeticMember);
	}

	@Override
	@Transactional
	public HashMap<String, Object> selectReviewList(CosmeticSearch search) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<>();
		List<CosmeticReview> reviewBest = cosmeticDao.selectReviewBest();
		List<CosmeticReview> reviewList =cosmeticDao.selectReviewList(search);
		resultMap.put("reviewBest", reviewBest);
		resultMap.put("reviewList", reviewList);
		return resultMap; 
	}

	@Override
	@Transactional
	public List<CosmeticReview> selectMainReviewList(CosmeticSearch search) throws Exception {
		return cosmeticDao.selectReviewList(search);
	}

	@Override
	@Transactional
	public int selectLogin(CosmeticMember member) throws Exception {
		return cosmeticDao.selectLogin(member);
	}

	@Override
	@Transactional
	public CosmeticMember selectMember(CosmeticMember member) throws Exception {
		return cosmeticDao.selectMember(member);
	}

	@Override
	@Transactional
	public int selectReviewMatch(CosmeticReview cosmeticReview) throws Exception {
		return cosmeticDao.selectReviewMatch(cosmeticReview);
	}

	@Override
	@Transactional
	public void deleteReview(CosmeticReview cosmeticReview) throws Exception {
		CosmeticWish cosmeticWish = new CosmeticWish();
		cosmeticDao.deleteReviewCommentByReviewNo(cosmeticReview.getReviewNo());
		cosmeticDao.deleteReviewPhoto(cosmeticReview.getReviewNo());
		cosmeticDao.deleteReview(cosmeticReview);
		//cosmeticWish.setMemberNo(cosmeticReview.getMemberNo());
		// 위시 있는지 여부부터 체크를 해야됨...
		
		//int wishNo = 0;
		//List<String> wno = cosmeticDao.selectWishGetNo(cosmeticReview.getReviewNo());
		//if(wno != null){
		//	wishNo = Integer.parseInt(wno);
			
		//}
		//wishNo = cosmeticDao.selectWishGetNo(cosmeticReview.getReviewNo());
		//cosmeticWish.setWishNo(wishNo);
		//cosmeticDao.deleteSugeryInfoByNo(wishNo);
		
		//cosmeticDao.deleteWishByNo(cosmeticReview.getReviewNo());
		//cosmeticDao.deleteReviewCommentByReviewNo(cosmeticReview.getReviewNo());
		//cosmeticDao.deleteReviewPhoto(cosmeticReview.getReviewNo());
		//cosmeticDao.deleteReviewRecom(cosmeticReview);
	}

	@Override
	@Transactional
	public void updateReview(CosmeticReview cosmeticReview) throws Exception {
		cosmeticDao.updateReview(cosmeticReview);
	}

	@Override
	@Transactional
	public List<CosmeticReviewPhoto> selectReviewPhotoNo(int reviewNo) throws Exception {
		return cosmeticDao.selectReviewPhotoNo(reviewNo);
	}

	@Override
	@Transactional
	public void updateReviewPhoto(CosmeticReviewPhoto cosmeticReviewPhoto) throws Exception {
		cosmeticDao.updateReviewPhoto(cosmeticReviewPhoto);
	}

	@Override
	@Transactional
	public HashMap<String, Object> insertReviewComment(HashMap<String, Object> paramMap) throws Exception {
		CosmeticSearch search = (CosmeticSearch)paramMap.get("search");
		CosmeticReviewComment cosmeticReviewComment = (CosmeticReviewComment)paramMap.get("cosmeticReviewComment");
		
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("commentNo", cosmeticDao.insertReviewComment(cosmeticReviewComment));
		int reviewNo = cosmeticReviewComment.getReviewNo();
		resultMap.put("commentList", cosmeticDao.selectReviewCommentByReviewNo(search));
		return resultMap;
	}

	@Override
	@Transactional
	public HashMap<String, Object> deleteReviewCommentByNo(HashMap<String, Object> paramMap) throws Exception {
		CosmeticSearch search = (CosmeticSearch)paramMap.get("search");
		CosmeticReviewComment cosmeticReviewComment = (CosmeticReviewComment)paramMap.get("cosmeticReviewComment");
		
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("resultCnt", cosmeticDao.deleteReviewCommentByNo(cosmeticReviewComment));
		resultMap.put("commentList", cosmeticDao.selectReviewCommentByReviewNo(search));
		return resultMap;
	}

	@Override
	@Transactional
	public List<CosmeticHospital> hospitalInfo() throws Exception {
		return cosmeticDao.selectHospital();
	}

	@Override
	@Transactional
	public HashMap<String, Object> insertWish(CosmeticWish cosmeticWish) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<>();
		int cnt = cosmeticDao.selectWishCheck(cosmeticWish);
		String msg = "fail";
		if(cnt == 0){
			msg = "success";
			cosmeticDao.insertWish(cosmeticWish);
			System.out.println("위시리스트 추가 완료");
		}
		
		resultMap.put("msg", msg);
		return resultMap;
	}

	@Override
	@Transactional
	public HashMap<String, Object> insertReviewRecom(CosmeticReviewRecom reviewRecom) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<>();
		int cnt = cosmeticDao.selectReviewRecomCheck(reviewRecom);
		String msg = "fail";
		if(cnt == 0){
			msg = "success";
			cosmeticDao.insertReviewRecom(reviewRecom);
			System.out.println("리뷰 추천 완료");
		}
		resultMap.put("msg", msg);
		
		return resultMap;
	}

	@Override
	@Transactional
	public List<CosmeticWish> selectReviewWish(int memberNo) throws Exception {
		return cosmeticDao.selectReviewWish(memberNo);
	}

	@Override
	@Transactional
	public HashMap<String, Object> insertSugeryInfo(int wishNo, int reviewNo) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<>();
		String msg = "fail";
		int ckCnt = cosmeticDao.selectInfoCheck(reviewNo);
		if(ckCnt != 0){
			CosmeticSugeryInfo cosmeticSugeryInfo = cosmeticDao.selectReviewSugeryInfo(reviewNo);
			cosmeticSugeryInfo.setWishNo(wishNo);
			cosmeticDao.insertSugeryInfo(cosmeticSugeryInfo);
			msg = "success";
		}
		
		resultMap.put("msg", msg);
		
		return resultMap;
	}
	
	@Override
	@Transactional
	public CosmeticReview selectSurgeryInfo(int reviewNo) throws Exception {
		return cosmeticDao.selectSurgeryInfo(reviewNo);
	}

	@Override
	@Transactional
	public void deleteWish(CosmeticWish wish) throws Exception {
		// 테이블 관계 때문에 위시번호에 관련된 수술정보 삭제
		//cosmeticDao.deleteSugeryInfo(wish);
		cosmeticDao.deleteWish(wish);
	}
	
	@Override
	@Transactional
	public List<CosmeticEvent> selectEventList(CosmeticSearch search) throws Exception {
		return cosmeticDao.selectEventList(search);
	}

	@Override
	@Transactional
	public void updateEventViewCnt(int eventNo) throws Exception {
		cosmeticDao.updateEventViewCnt(eventNo);
	}

	@Override
	@Transactional
	public int selectEventViewCnt(int eventNo) throws Exception {
		return cosmeticDao.selectEventViewCnt(eventNo);
	}

	@Override
	@Transactional
	public void insertWishEvent(CosmeticWishEvent wishEvent) throws Exception {
		cosmeticDao.insertWishEvent(wishEvent);	
	}

	@Override
	@Transactional
	public int selectWishEvent(CosmeticWishEvent wishEvent) throws Exception {
		return cosmeticDao.selectWishEvent(wishEvent);	
	}

	@Override
	@Transactional
	public List<CosmeticSugeryInfo> selectSurgeryInfoByMemberNo(int memberNo) throws Exception {
		return cosmeticDao.selectSurgeryInfoByMemberNo(memberNo);
	}
	
	
	@Override
	@Transactional
	public CosmeticHospital selectHospitalInfoDetail(int hospitalNo) throws Exception {
		return cosmeticDao.selectHospitalInfoDetail(hospitalNo); 
	}

	@Override
	@Transactional
	public List<CosmeticHospital> selectHospitalInfoByName(String name) throws Exception {
		return cosmeticDao.selectHospitalInfoByName(name);
	}

	@Override
	@Transactional
	public void insertHospital(CosmeticHospital cosmeticHospital) throws Exception {
		cosmeticDao.insertHospital(cosmeticHospital);
	}

	@Override
	@Transactional
	public void insertEvent(CosmeticEvent cosmeticEvent) throws Exception {
		cosmeticDao.insertEvent(cosmeticEvent);
		
	}

	@Override
	@Transactional
	public List<CosmeticQnA> selectQnA(int mNo) throws Exception {
		return cosmeticDao.selectQnA(mNo);
	}

	@Override
	@Transactional
	public void insertQnA(CosmeticQnA cosmeticQnA) throws Exception {
		cosmeticDao.insertQnA(cosmeticQnA);
	}

	@Override
	@Transactional
	public void insertCounsel(CosmeticCounsel cosmeticCounsel) throws Exception {
		cosmeticDao.insertCounsel(cosmeticCounsel);
	}
	
	@Override
	@Transactional
	public CosmeticQnA detailQnA(int qno) throws Exception {
		return cosmeticDao.selectQnAByNo(qno);
	}

	@Override
	@Transactional
	public void insertCounselPhoto(CosmeticCounselPhoto cosmeticCounselPhoto) throws Exception {
		cosmeticDao.insertCounselPhoto(cosmeticCounselPhoto);
	}
	
	@Override
	@Transactional
	public void deleteQnA(int qno) throws Exception {
		cosmeticDao.deleteQnA(qno);
	}

	@Override
	@Transactional
	public List<CosmeticCounselPhoto> selectCounselPhoto(int counselNo) throws Exception {
		return cosmeticDao.selectCounselPhoto(counselNo);
	}

	@Override
	@Transactional
	public void updateQnA(CosmeticQnA cosmeticQnA) throws Exception {
		cosmeticDao.updateQnA(cosmeticQnA);
	}
}
