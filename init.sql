-- User Schema
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Content Schema
CREATE TABLE content (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         title VARCHAR(255) NOT NULL,
                         description TEXT,
                         type VARCHAR(50) NOT NULL,
                         metadata JSONB,
                         created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- User Interactions Table
CREATE TABLE user_interactions (
                                   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                   user_id UUID REFERENCES users(id),
                                   content_id UUID REFERENCES content(id),
                                   interaction_type VARCHAR(50) NOT NULL,
                                   created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                   metadata JSONB
);

-- Create indexes
CREATE INDEX idx_user_interactions_user_id ON user_interactions(user_id);
CREATE INDEX idx_user_interactions_content_id ON user_interactions(content_id);
CREATE INDEX idx_content_type ON content(type);