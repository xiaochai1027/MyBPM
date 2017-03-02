package com.i360r.bpm.business.model;

public enum ProcessUniqueScope {

	/**	同一发起人未完成的流程 */
	CREATOR_UNFINISHED,
	/**	未完成的流程和已完成并通过的流程 */
	UNFINISHED_PASS,
	/**	未完成的流程 */
	UNFINISHED;
}
