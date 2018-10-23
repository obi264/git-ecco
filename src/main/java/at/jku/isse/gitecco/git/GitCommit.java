package at.jku.isse.gitecco.git;

import java.util.Collections;
import java.util.List;

/**
 * Class for handling commits and be able to
 * distinguish between normal commits, branch points and merges.
 */
public class GitCommit {
    private final String commitName;
    private final List<GitCommitType> types;
    private final String branch;

    /**
     * Creates a new GitCommit
     * @param commitName
     * @param types
     * @param branch
     */
    public GitCommit(String commitName, List<GitCommitType> types, String branch) {
        this.commitName = commitName;
        this.types = types;
        this.branch = branch;
    }

    /**
     * Gets the brnach of the commit
     * @return
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Gets the SHA1 ID aka the name of the commit.
     * @return the commit name / SHA1 ID
     */
    public String getCommitName() {
        return commitName;
    }

    /**
     * Gets the type of the commit: commit/branch/merge
     * @return
     */
    public List<GitCommitType> getType() {
        return Collections.unmodifiableList(types);
    }
}
